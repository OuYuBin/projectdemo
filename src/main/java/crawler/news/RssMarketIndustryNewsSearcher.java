package crawler.news;
import org.dom4j.Element;

public class RssMarketIndustryNewsSearcher extends FeedSearcher implements
		MarketIndustryNewsSearcher {

	@Override
	protected RssItem parseElement(Element element) {
		News cn = new News();
		parseBasicElement(element, cn);
		return cn;
	}

	public static void main(String[] args) {
		MarketIndustryNewsSearcherRunner.run(
				new RssMarketIndustryNewsSearcher(), args);
	}

}
