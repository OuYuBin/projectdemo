package crawler.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import crawler.news.CategoryNews;
import crawler.news.Company;

public class DBCompanyNewsDAO implements CompanyNewsDAO {

	@Override
	public List<Company> getCompanies() {
		List<Company> result = new ArrayList<Company>();
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cnn = JDBCUtil.getConnection();
			cs = cnn.prepareCall("{call proc_company_sel()}");
			rs = cs.executeQuery();
			while (rs.next()) {
				result.add(parseToCompany(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, cs);
		}

		return result;
	}

	@Override
	public List<Company> getChinaADRCompanies() {
		return getCompaniesByProc("proc_adr_company_sel");
	}

	@Override
	public List<Company> getNoneChinaADRCompanies() {
		return getCompaniesByProc("proc_noneadr_company_sel");
	}

	@Override
	public void saveCompanyNews(Company com) {
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cnn = JDBCUtil.getConnection();
			for (CategoryNews cn : com.getNews()) {
				cs = cnn.prepareCall("{call proc_stock_news_ins(?,?,?,?,?,?,?)}");
				cs.setInt(1, com.getMsid());
				cs.setString(2, com.getSymbol());
				cs.setString(3, cn.getTitle());
				cs.setString(4, cn.getUrl());
				cs.setTimestamp(5, new Timestamp(cn.getTime()));
				cs.setString(6, cn.getSource());
				cs.setString(7, cn.getCategory());
				cs.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, cs);
		}
	}

	@Override
	public void saveNews(CategoryNews news) {
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cnn = JDBCUtil.getConnection();
			cs = cnn.prepareCall("{call proc_general_news_ins(?,?,?,?,?)}");
			cs.setString(1, news.getTitle());
			cs.setString(2, news.getUrl());
			cs.setTimestamp(3, new Timestamp(news.getTime()));
			cs.setString(4, news.getSource());
			cs.setString(5, news.getCategory());
			cs.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, cs);
		}
	}

	public List<Company> getCompaniesByProc(String proc) {
		List<Company> result = new ArrayList<Company>();
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cnn = JDBCUtil.getConnection();
			cs = cnn.prepareCall("{call " + proc + "()}");
			rs = cs.executeQuery();
			while (rs.next()) {
				result.add(parseToCompany(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, cs);
		}

		return result;
	}

	private Company parseToCompany(ResultSet rs) throws SQLException {
		Company com = new Company();
		com.setMsid(rs.getInt(1));
		com.setSymbol(rs.getString(2));
		return com;
	}

}
