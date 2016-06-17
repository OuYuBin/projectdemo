package crawler.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.BulletList;
import org.htmlparser.tags.Div;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.ObjectFindingVisitor;

import crawler.dao.CompanyNewsDAO;
import crawler.dao.DBCompanyNewsDAO;
import crawler.dao.JDBCUtil;
import crawler.util.UrlUtil;


public class SinaStockSearcher extends HtmlSearcher {

	/* template of URL to search symbol */
	private static final String URL_TEMPL = "http://biz.finance.sina.com.cn/usstock/usstock_news.php?symbol=#SYMBOL#";
	private static final String SYMBOL_FLAG = "#SYMBOL#";
	private static final String SOURCE = "sina";

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

		System.out.println("Sina feed: " + searchUrl);
		List<String> cats = getCategories(newsLists);
		int catIndex = 0;
		for (BulletList newsList : newsLists) {
			Node[] nodes = newsList.getChildrenAsNodeArray();
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] instanceof Bullet) {
					CategoryNews news = new CategoryNews();
					setNewsContent(nodes[i], news);
					news.setCategory(cats.get(catIndex));
					news.setSource(SOURCE);
					if (news.isValid()) {
						com.addNews(news);
					} else {
						System.err
								.println("Invalid news: " + nodes[i].toHtml());
					}
				}
			}

			catIndex++;
		}
	}

	private List<BulletList> getNewsLists(String url) throws ParserException,
			IOException {
		List<BulletList> bls = new ArrayList<BulletList>();
		// specify GBK encoding to get Chinese content
		Parser parser = new Parser(UrlUtil.getUrlContent(url));
		ObjectFindingVisitor visitor = new ObjectFindingVisitor(
				BulletList.class);
		parser.visitAllNodesWith(visitor);
		Node[] nodes = visitor.getTags();
		for (int i = 0; i < nodes.length; i++) {
			if (isNewsBulletList(nodes[i])) {
				bls.add((BulletList) nodes[i]);
			}
		}

		return bls;
	}

	private List<String> getCategories(List<BulletList> newsLists) {
		List<String> result = new ArrayList<String>();
		if (newsLists.size() == 0) {
			return result;
		}

		int countOfNewsLists = 0;
		NodeList children = newsLists.get(0).getParent().getChildren();
		for (int i = 0; i < children.size(); i++) {
			Node child = children.elementAt(i);
			if (child instanceof Div) {
				Div div = (Div) child;
				String clsVal = div.getAttribute("class");
				if (clsVal != null && clsVal.equals("title")) {
					result.add(getCategory(div));
				}
			} else if (child instanceof BulletList && isNewsBulletList(child)) {
				countOfNewsLists++;
			}
		}

		// top section doesn't have title
		if (result.size() < countOfNewsLists) {
			result.add(0, null);
		}

		return result;
	}

	private boolean isNewsBulletList(Node node) {
		BulletList bl = (BulletList) node;
		String attr = bl.getAttribute("class");
		if (attr != null && attr.equals("xb_list")) {
			return true;
		}

		return false;
	}

	private String getCategory(Div div) {
		if (div.toHtml().contains("公司研究")) {
			return CategoryNews.CATEGORY_RECOMMENDATION;
		} else if (div.toHtml().contains("公司资讯")) {
			return CategoryNews.CATEGORY_NEWS;
		} else {
			return null;
		}
	}

	@Override
	protected boolean isTime(Tag node) {
		String attr = node.getAttribute("class");
		if (attr != null && attr.equals("xb_list_r")) {
			return true;
		}

		return false;
	}

	// e.g. 新浪科技 | 2014年07月23日 22:41
	@Override
	protected String getTimeText(String html) {
		return html.replaceAll(".*\\|", "").trim();
	}

	public static void main(String[] args) {
		CompanyNewsDAO dao = new DBCompanyNewsDAO();
		SinaStockSearcher ss = new SinaStockSearcher();
		List<Company> coms = dao.getChinaADRCompanies();
		for (Company com : coms) {
			ss.searchCompany(com);
			dao.saveCompanyNews(com);
		}

		System.out.println("SinaSearcher finished");
		JDBCUtil.closeConnection();
	}

}
