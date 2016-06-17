package crawler.news;

import java.util.List;

import org.htmlparser.Node;

import crawler.dao.CompanyNewsDAO;
import crawler.dao.DBCompanyNewsDAO;


public class QQFinanceSearcher extends NewsListSearcher {

	private static final String URL = "http://finance.qq.com/usstock/zggn/index.htm?pgv_ref=fi_quote_navi_bar";
	private static final String SOURCE = "qq";

	@Override
	protected String getTimeText(String html) {
		return html.replaceAll("<[^>]+>", "").replace("&#160;", " ").trim();
	}

	@Override
	protected News parseToNews(Node node) {
		CategoryNews news = new CategoryNews();
		setNewsContent(node, news);
		news.setCategory(CategoryNews.CATEGORY_NEWS);
		news.setSource(SOURCE);
		return news;
	}

	@Override
	protected String getDivClass() {
		return "mod newslist";
	}

	public static void main(String[] args) {
		//CompanyNewsDAO dao = new ConsoleCompanyNewsDAO();
		CompanyNewsDAO dao = new DBCompanyNewsDAO();
		QQFinanceSearcher qfs = new QQFinanceSearcher();
		List<RssItem> newsList = qfs.search(URL);
		for (RssItem news : newsList) {
			//dao.saveNews((CategoryNews) news);
			dao.saveNews((CategoryNews)news);
		}

		System.out.println("QQFinanceSearcher found news: " + newsList.size());
	}

}
