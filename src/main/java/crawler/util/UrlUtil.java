package crawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil {

	public static String getUrlContent(String url) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				getConnection(url).getInputStream(), "GB2312"));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}
	
	public static InputStream getInputStream(String url) throws IOException {
		return getConnection(url).getInputStream();
	}

	public static HttpURLConnection getConnection(String url)
			throws IOException {
		HttpURLConnection huc = (HttpURLConnection) new URL(url)
				.openConnection();

		// 30 seconds
		int timeout = 30 * 1000;
		huc.setConnectTimeout(timeout);
		huc.setReadTimeout(timeout);
		huc.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 5.1; rv:22.0) Gecko/20100101 Firefox/22.0");
		return huc;
	}
}
