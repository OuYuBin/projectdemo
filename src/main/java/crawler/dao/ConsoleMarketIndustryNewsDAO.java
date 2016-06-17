package crawler.dao;

import java.util.Date;

import crawler.news.News;


public class ConsoleMarketIndustryNewsDAO implements MarketIndustryNewsDAO {

	@Override
	public void insertNews(News news) {
		System.out.println(news.getTitle() + news.getUrl() + new Date(news.getTime()));
	}

}
