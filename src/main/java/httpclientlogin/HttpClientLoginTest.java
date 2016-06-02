package httpclientlogin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class HttpClientLoginTest {
	@Test
	// 获取一个HTML页面的内容，一个简单的get应用
	public void grabpageHTML() throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://www.baidu.com/");
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		String html = EntityUtils.toString(entity, "GBK");
		httpGet.releaseConnection();

		System.out.println(html);
	}

	// 下载一个文件到本地（本示范中为一个验证码图片）
	@Test
	public void downloadFile() throws ClientProtocolException, IOException {
		String url = "http://www.lashou.com/account/captcha";
		File dir = new File("D:\\download");
		if (!dir.exists()) {
			dir.mkdir();
		}
		String destfilename = "D:\\download\\yz.png";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		File file = new File(destfilename);
		if (file.exists()) {
			file.delete();
		}
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream inputStream = entity.getContent();
		try {
			FileOutputStream fout = new FileOutputStream(file);
			byte[] tmp = new byte[2048];
			while ((inputStream.read(tmp)) != -1) {
				fout.write(tmp);
			}
			fout.close();
		} catch (Exception e) {

		} finally {
			inputStream.close();
		}
		httpGet.releaseConnection();
	}

	// Post方法，模拟表单提交参数登录到网站。
	// 结合了上面两个方法：grabPageHTML/downloadFile，同时增加了Post的代码。
	@Test
	public void login2LaShou() throws ClientProtocolException, IOException {
		// 第一步：先下载验证码到本地
		/*
		 * String url = "http://www.lashou.com/account/captcha"; String
		 * destfilename = "D:\\download\\yz.png"; HttpClient httpclient = new
		 * DefaultHttpClient(); HttpGet httpget = new HttpGet(url); File file =
		 * new File(destfilename); if (file.exists()) { file.delete(); }
		 * 
		 * HttpResponse response = httpclient.execute(httpget); HttpEntity
		 * entity = response.getEntity(); InputStream in = entity.getContent();
		 * try { FileOutputStream fout = new FileOutputStream(file); int l = -1;
		 * byte[] tmp = new byte[2048]; while ((l = in.read(tmp)) != -1) {
		 * fout.write(tmp); } fout.close(); } finally { in.close(); }
		 * httpget.releaseConnection();
		 */

		// 第二步：用Post方法带若干参数尝试登录，需要手工输入下载验证码中显示的字母、数字
		/*
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in));
		 * System.out.println("请输入下载下来的验证码中显示的数字..."); String yan =
		 * br.readLine();
		 */

		HttpClient httpclient = new DefaultHttpClient();

		// HttpPost httpPost = new
		// HttpPost("http://sws.hengtiansoft.com/sws/web/auth/login/authc");///web/auth/login/authc
		HttpPost httpPost = new HttpPost(
				"http://172.17.17.89:8080/sws/web/auth/login/authc");// /web/auth/login/authc

		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

		SignInDto signInDto = new SignInDto();
		signInDto.setUserName("thinkgem");
		signInDto.setPassword("admin");
		signInDto.setRememberMe(false);

		String jsonString = JSON.toJSONString(signInDto);
		System.out.println("jsonString" + jsonString);

		StringEntity se = new StringEntity(jsonString);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));

		httpPost.setEntity(se);

		List<NameValuePair> params = new ArrayList<NameValuePair>();
		/*
		 * params.add(new BasicNameValuePair("user_id", "testuser007"));
		 * params.add(new BasicNameValuePair("pwd", "asdfg123")); params.add(new
		 * BasicNameValuePair("yan", yan)); params.add(new
		 * BasicNameValuePair("save_user", "on")); params.add(new
		 * BasicNameValuePair("save_pwd", "on")); params.add(new
		 * BasicNameValuePair("sub", "登录"));
		 */

		// params.add(new BasicNameValuePair("signInDto", jsonString));
		/*
		 * params.add(new BasicNameValuePair("userName","thinkgem"));
		 * params.add(new BasicNameValuePair("password", "admin"));
		 */
		// params.add(new BasicNameValuePair("rememberMe", "false"));

		// httpPost.setEntity(new UrlEncodedFormEntity(params));
		// httpPost.setHeader("Content-type", "application/json");

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		// 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
		String postResult = EntityUtils.toString(entity, "GBK");
		System.out.println(postResult);
		System.out
				.println("=======================================================================");
		System.out
				.println("=======================================================================");
		CookieStore cookieStore = ((AbstractHttpClient) httpclient)
				.getCookieStore();
		List<Cookie> cookies = ((AbstractHttpClient) httpclient)
				.getCookieStore().getCookies();
		for (Cookie cookie : cookies) {
			System.out.println("cookie begin***\n" + cookie + "\n cookie end");
			;
		}
		httpPost.releaseConnection();

		// 第三步：打开会员页面以判断登录成功（未登录用户是打不开会员页面的）
		String memberpage = "http://sws.hengtiansoft.com/sws/web/index/tostation?stationId=1";
		HttpGet httpget = new HttpGet(memberpage);
		response = httpclient.execute(httpget);
		entity = response.getEntity();
		String html = EntityUtils.toString(entity, "GBK");
		httpget.releaseConnection();

		System.out.println(html);
	}
}
