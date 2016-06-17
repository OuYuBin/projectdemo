package crawler.news;

import java.io.IOException;
import java.util.List;

import crawler.dao.DBMarketIndustryNewsDAO;
import crawler.dao.JDBCUtil;
import crawler.dao.MarketIndustryNewsDAO;
import crawler.util.FileFeedReader;


public class MarketIndustryNewsSearcherRunner {
	private static final String QQ_SOURCE = "qq";
	private static final String BD_SOURCE = "baidu";
	private static final String SN_SOURCE = "sina";

	public static void run(MarketIndustryNewsSearcher searcher, String[] args) {
		
		if (args.length < 1) {
			System.err.println("Miss RSS feed file argument.");
			System.exit(1);
		}

		MarketIndustryNewsDAO dao = new DBMarketIndustryNewsDAO();
		List<String> feeds;
		try {
			feeds = FileFeedReader.readFeed(args[0]);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to read source feeds.");
			return;
		}
		String sour= "";
		for (String feed : feeds) {
			if (feed.startsWith("[")){
				sour = feed.replace("[", "");
				continue;
			}
			System.out.println("feed: "+feed);
			List<RssItem> news = searcher.search(feed);
			for (RssItem newsItme : news) {
				if(newsItme.getUrl().contains("qq.")||newsItme.getUrl().contains("sina.")||newsItme.getUrl().contains("baidu.")){
					if(newsItme.getUrl().contains("qq."))
						newsItme.setSource(QQ_SOURCE);
					if(newsItme.getUrl().contains("baidu"))
						newsItme.setSource(BD_SOURCE);
					if(newsItme.getUrl().contains("sina"))
						newsItme.setSource(SN_SOURCE);
				}else {
				if (sour.equals("qq"))
				    newsItme.setSource(QQ_SOURCE);
				if (sour.equals("baidu"))
					newsItme.setSource(BD_SOURCE);
				if (sour.equals("sina"))
					newsItme.setSource(SN_SOURCE);
				
			}
				dao.insertNews((News) newsItme);
				//System.out.println("news: "+newsItme.getTitle().toString());
			}
		}

		JDBCUtil.closeConnection();
	}

}
