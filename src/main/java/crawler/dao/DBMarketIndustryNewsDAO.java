package crawler.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import crawler.news.News;

public class DBMarketIndustryNewsDAO implements MarketIndustryNewsDAO {

	@Override
	public void insertNews(News news) {
		Connection cnn = null;
		CallableStatement cs = null;
		try {
		cnn = JDBCUtil.getConnection();
		cs = cnn.prepareCall("{call proc_market_industry_news_ins(?,?,?,?)}");
		cs.setString(1, news.getUrl());
		cs.setString(2, news.getTitle());
		cs.setString(3, news.getSource());
		cs.setTimestamp(4, new Timestamp(news.getTime()));
		cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(null, cs);
		}
	}

}
