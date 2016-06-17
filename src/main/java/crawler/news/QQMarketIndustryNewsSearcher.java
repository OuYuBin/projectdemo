package crawler.news;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.HeadingTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.tags.ParagraphTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import crawler.util.HtmlParserUtil;
import crawler.util.UrlUtil;


public class QQMarketIndustryNewsSearcher extends HtmlSearcher implements
		MarketIndustryNewsSearcher {

	private static final String URL_PREFIX = "http://finance.qq.com";

	@Override
	public List<RssItem> search(String url) {
		List<RssItem> result = new ArrayList<RssItem>();
		try {
			List<Div> divs = HtmlParserUtil.findDivByClass(
					UrlUtil.getUrlContent(url), "nrC");

			for (Div div : divs) {
				News news = new News();
				setNewsContent(div, news);
				refineUrl(news);
				result.add(news);
			}
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	protected boolean isTime(Tag child) {
		return child instanceof ParagraphTag;
	}

	@Override
	protected String getTimeText(String html) {
		return html.replaceAll(".*\\|", "").replaceAll("<[^>]+>", "")
				.replace("\"", "").trim();
	}

	@Override
	protected LinkTag getLinkTag(Node node) {
		if (node instanceof HeadingTag) {
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

	private void refineUrl(News news) {
		String url = news.getUrl();
		if (!url.startsWith("http://")) {
			news.setUrl(URL_PREFIX + url);
		}
	}

	public static void main(String[] args) {
		MarketIndustryNewsSearcherRunner.run(
				new QQMarketIndustryNewsSearcher(), args);
	}

}
