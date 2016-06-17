package crawler.news;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;

import crawler.dao.ConsoleMarketIndustryNewsDAO;
import crawler.dao.MarketIndustryNewsDAO;


public class SinaRollHtmlSearcher extends NewsListSearcher {

	private static final String URL = "http://roll.finance.sina.com.cn/s/channel.php?ch=03#col=49&spec=&type=&ch=03&k=&offset_page=0&offset_num=0&num=60&asc=&page=1";

	@Override
	protected News parseToNews(Node node) {
		News news = new News();
		setNewsContent(node, news);
		return news;
	}

	protected LinkTag getLinkTag(Node node) {
		if (node instanceof Span
				&& "c_tit".equals(((Span) node).getAttribute("class"))) {
			NodeList children = node.getChildren();
			for (int i = 0; i < children.size(); i++) {
				Node child = children.elementAt(i);
				if (child instanceof LinkTag) {
					return (LinkTag) child;
				}
			}
		}

		return null;
	}

	@Override
	protected boolean isTime(Tag child) {
		return child instanceof Span
				&& "c_time".equals(child.getAttribute("class"));
	}

	@Override
	protected String getDivClass() {
		return "d_list_txt";
	}

	@Override
	protected String getTimeText(String html) {
		return html.replaceAll("<[^>]+>", "").trim();
	}

	public static void main(String[] args) {
		MarketIndustryNewsDAO dao = new ConsoleMarketIndustryNewsDAO();
		SinaRollHtmlSearcher srhs = new SinaRollHtmlSearcher();
		List<RssItem> newsList = srhs.search(URL);
		int newsCount = 0;
		for (RssItem news : newsList) {
			dao.insertNews((News) news);
			newsCount++;
		}

		System.out.println("SinaRollHtmlSearcher found news: " + newsCount);
	}

}
