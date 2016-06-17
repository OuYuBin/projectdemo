package crawler.news;

import org.htmlparser.Node;

public class SinaHtmlSearcher extends NewsListSearcher implements
		MarketIndustryNewsSearcher {

	@Override
	protected News parseToNews(Node node) {
		News news = new News();
		setNewsContent(node, news);
		return news;
	}

	@Override
	protected String getDivClass() {
		return "listBlk";
	}

	@Override
	protected String getTimeText(String html) {
		return html.replaceAll("<[^>]+>", "").replace("&#160;", " ")
				.replace("(", "").replace(")", "").trim();
	}

	public static void main(String[] args) {
		MarketIndustryNewsSearcherRunner.run(new SinaHtmlSearcher(), args);
	}

}
