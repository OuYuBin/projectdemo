package crawler.news;

import java.util.List;

public interface MarketIndustryNewsSearcher {

	List<RssItem> search(String url);

}
