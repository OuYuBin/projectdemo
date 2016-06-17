package crawler.news;

import java.util.List;

import org.dom4j.Element;

import crawler.dao.CompanyNewsDAO;
import crawler.dao.DBCompanyNewsDAO;
import crawler.dao.JDBCUtil;

public class YahooRssSearcher extends FeedSearcher {

	private static final String URL_TEMPL = "http://feeds.finance.yahoo.com/rss/2.0/headline?s=#SYMBOL#&region=US&lang=en-US";
	private static final String SYMBOL_FLAG = "#SYMBOL#";
	private static final String SOURCE = "yahoo";

	public String getSearchUrl(Company com) {
		return URL_TEMPL.replace(SYMBOL_FLAG, com.getSymbol());
	}

	@Override
	protected RssItem parseElement(Element element) {
		CategoryNews news = new CategoryNews();
		parseBasicElement(element, news);
		news.setCategory(CategoryNews.CATEGORY_NEWS);
		return news;
	}

	public static void main(String[] args) {
		CompanyNewsDAO dao = new DBCompanyNewsDAO();
		YahooRssSearcher yrs = new YahooRssSearcher();
		List<Company> coms = dao.getNoneChinaADRCompanies();
		for (Company com : coms) {
			String url = yrs.getSearchUrl(com);
			List<RssItem> newsList = yrs.search(url);
			for (RssItem newsItem : newsList) {
				CategoryNews news = (CategoryNews) newsItem;
				news.setSource(SOURCE);
				com.addNews(news);
			}

			dao.saveCompanyNews(com);
		}

		System.out.println("YahooRssSearcher finished");
		JDBCUtil.closeConnection();
	}

}
