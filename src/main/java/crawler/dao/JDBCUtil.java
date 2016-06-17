package crawler.dao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public final class JDBCUtil {

	private static final String CONFIG_FILE_NAME = "jdbc.properties";

	private static Connection connection;

	private static String driver;

	private static String url;

	private static String user;

	private static String password;

	static {
		try {
			loadProperties();
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);

		} catch (IOException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(url, user, password);
		}

		return connection;
	}

	public static void free(ResultSet rs, Statement st) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null)
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private static void loadProperties() throws IOException {
		/*Properties prop = new Properties();
		prop.load(JDBCUtil.class.getClassLoader().getResourceAsStream(
				CONFIG_FILE_NAME));*/

	/*	driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		user = prop.getProperty("user");
		password = prop.getProperty("password");*/
		driver ="com.mysql.jdbc.Driver";
		url = "jdbc:mysql://wogutandevdb:3306/rss?characterEncoding=utf-8";
		user = "root";
		password = "123456";
	}
}
