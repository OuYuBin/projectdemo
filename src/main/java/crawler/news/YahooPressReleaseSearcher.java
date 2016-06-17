package crawler.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.BulletList;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import crawler.dao.CompanyNewsDAO;
import crawler.dao.DBCompanyNewsDAO;
import crawler.dao.JDBCUtil;
import crawler.util.HtmlParserUtil;


public class YahooPressReleaseSearcher extends HtmlSearcher {

	private static final String URL_TEMPL = "http://finance.yahoo.com/q/p?s=#SYMBOL#+Press+Releases";
	private static final String SYMBOL_FLAG = "#SYMBOL#";
	private static final String SOURCE = "yahoo";

	public void searchCompany(Company com) {
		String searchUrl = URL_TEMPL.replace(SYMBOL_FLAG, com.getSymbol());
		List<BulletList> newsLists;
		try {
			newsLists = getNewsLists(searchUrl);
		} catch (ParserException e) {
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		System.out.println("Yahoo feed: " + searchUrl);
		for (BulletList newsList : newsLists) {
			Node[] nodes = newsList.getChildrenAsNodeArray();
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] instanceof Bullet) {
					CategoryNews news = new CategoryNews();
					setNewsContent(nodes[i], news);
					news.setCategory(CategoryNews.CATEGORY_PRESS_RELEASE);
					news.setSource(SOURCE);
					if (news.isValid()) {
						com.addNews(news);
					} else {
						System.err
								.println("Invalid news: " + nodes[i].toHtml());
					}
				}
			}
		}
	}

	private List<BulletList> getNewsLists(String url) throws ParserException,
			IOException {
		List<BulletList> bls = new ArrayList<BulletList>();
		List<Div> divs = getNewsDiv(url);
		if (divs.size() == 0) {
			System.out.println("Failed to find news div: " + url);
			return bls;
		}

		Div div = divs.get(0);
		NodeList children = div.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Node child = children.elementAt(i);
			if (children.elementAt(i) instanceof BulletList) {
				BulletList bl = (BulletList) child;
				// useless ul:
				// <ul class="yfncnhl newsheadlines"></ul>
				String attr = bl.getAttribute("class");
				if (attr == null) {
					bls.add(bl);
				}
			}
		}

		return bls;
	}

	private List<Div> getNewsDiv(String url) throws ParserException {
		return HtmlParserUtil.findDivByClass(url,
				"mod yfi_quote_headline withsky");
	}

	// only has two child: link and span of time
	@Override
	protected boolean isTime(Tag child) {
		return child instanceof Span;
	}

	@Override
	protected String getTimeText(String html) {
		return html.replaceAll(".*\\(", "").replaceAll("\\).*", "").trim();
	}

	public static void main(String[] args) {
		CompanyNewsDAO dao = new DBCompanyNewsDAO();
		YahooPressReleaseSearcher yprs = new YahooPressReleaseSearcher();
		List<Company> coms = dao.getCompanies();
		for (Company com : coms) {
			yprs.searchCompany(com);
			dao.saveCompanyNews(com);
		}

		JDBCUtil.closeConnection();
	}

}
