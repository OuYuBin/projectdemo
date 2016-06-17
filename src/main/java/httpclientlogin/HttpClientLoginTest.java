package httpclientlogin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
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
		//HttpPost httpPost = new HttpPost("http://sws.hengtiansoft.com/sws/web/auth/login/authc");///web/auth/login/authc
		HttpPost httpPost = new HttpPost("http://sws.hengtiansoft.com/sws/web/auth/login/authc");// /web/auth/login/authc
		//HttpPost httpPost = new HttpPost("https://passport.liepin.com/c/login.json");
		//HttpPost httpPost = new HttpPost("https://mylogin.51job.com/40621842618756489796/my/My_Pmc.php");
		
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

		SignInDto signInDto = new SignInDto();
		signInDto.setUserName("thinkgem");
		signInDto.setPassword("admin");
		signInDto.setRememberMe(false);
		
	/*	LiePinSignInDto liePinSignInDto=new LiePinSignInDto();
		liePinSignInDto.setUserLogin("m15167101121@163.com");
		liePinSignInDto.setUserPwd("131415926");
		liePinSignInDto.setChkRememberPwd(false);*/

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
		//String memberpage = "https://sns.liepin.com/main/";
		HttpGet httpget = new HttpGet(memberpage);
		response = httpclient.execute(httpget);
		entity = response.getEntity();
		String html = EntityUtils.toString(entity, "GBK");
		httpget.releaseConnection();

		System.out.println(html);
	}
	
	@Test
	public void login2LiePin() throws ParseException, IOException{
		HttpClient httpclient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost("https://passport.liepin.com/c/login.json");
		
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		httpPost.addHeader("x-requested-with", "XMLHttpRequest");
		LiePinSignInDto liePinSignInDto=new LiePinSignInDto();
		liePinSignInDto.setLayer_from("wwwindex_rightbox_new");
		liePinSignInDto.setUser_login("15167101121");
		liePinSignInDto.setUser_pwd("dcdab9faab89a3d04b6c9050aaafa10b");
		liePinSignInDto.setChk_remember_pwd("");

		String jsonString = JSON.toJSONString(liePinSignInDto);
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
		//String memberpage = "http://sws.hengtiansoft.com/sws/web/index/tostation?stationId=1";
		String memberpage = "https://sns.liepin.com/main/";
		HttpGet httpget = new HttpGet(memberpage);
		response = httpclient.execute(httpget);
		entity = response.getEntity();
		String html = EntityUtils.toString(entity, "GBK");
		httpget.releaseConnection();

		//System.out.println(html);
	}
	
	@Test
	public void login2LiePinAgain() throws ParseException, IOException{
		
		HttpClient client = new DefaultHttpClient();                //构建一个Client
		HttpPost post = new HttpPost("https://passport.liepin.com/c/login.json");    //构建一个POST请求
	 //   post.addHeader("content-type", "application/x-www-form-urlencoded");
	    post.addHeader("x-requested-with", "XMLHttpRequest");
		//构建表单参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    nvps.add(new BasicNameValuePair("layer_from", "wwwindex_rightbox_new"));  
	    nvps.add(new BasicNameValuePair("user_login", "m15167101121@163.com"));  
	    nvps.add(new BasicNameValuePair("user_pwd", "dcdab9faab89a3d04b6c9050aaafa10b"));  
	    nvps.add(new BasicNameValuePair("chk_remember_pwd", ""));  
	    /*nvps.add(new BasicNameValuePair("user_kind", "0"));  
	    nvps.add(new BasicNameValuePair("isMd5", "1")); */ 
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");//将表单参数转化为“实体”
		post.setEntity(entity); //将“实体“设置到POST请求里
	    
		HttpResponse response = client.execute(post);//提交POST请求
		HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
		String content = EntityUtils.toString(result); //用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
		System.out.println(content);
		
		String memberpage = "https://c.liepin.com";
		HttpGet httpget = new HttpGet(memberpage);
		response = client.execute(httpget);
		HttpEntity entitys =  response.getEntity();
		String html = EntityUtils.toString(entitys, "GBK");
		httpget.releaseConnection();

		System.out.println(html);
		
		
	}
	@Test
	public void login2Job51() throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();                //构建一个Client
		HttpPost post = new HttpPost("https://mylogin.51job.com/65119966868607969823/my/My_Pmc.php");    //构建一个POST请求
		//构建表单参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    //nvps.add(new BasicNameValuePair("layer_from", "wwwindex_rightbox_new"));  
	    nvps.add(new BasicNameValuePair("username", "15167101121"));  
	    nvps.add(new BasicNameValuePair("userpwd", "jia131415926"));  
	    nvps.add(new BasicNameValuePair("login_verify", ""));  
	    nvps.add(new BasicNameValuePair("url", ""));  
	    nvps.add(new BasicNameValuePair("x", "56"));  
	    nvps.add(new BasicNameValuePair("y", "21"));  
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");//将表单参数转化为“实体”
		post.setEntity(entity); //将“实体“设置到POST请求里
		            
		HttpResponse response = client.execute(post);//提交POST请求
		HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
		String content = EntityUtils.toString(result);; //用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
		System.out.println(content);
		
		String memberpage = "http://my.51job.com/my/113940355/My_Pmc.php";
		HttpGet httpget = new HttpGet(memberpage);
		response = client.execute(httpget);
		HttpEntity entitys =  response.getEntity();
		String html = EntityUtils.toString(entitys, "GBK");
		httpget.releaseConnection();

		System.out.println(html);
		
	}
	
	@Test
	public void login2ZhiLian() throws ParseException, IOException{
		//trustAllHosts();
		HttpClient client = new DefaultHttpClient();                //构建一个Client
		HttpPost post = new HttpPost("https://passport.zhaopin.com/account/login");    //构建一个POST请求
		//构建表单参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	    //nvps.add(new BasicNameValuePair("layer_from", "wwwindex_rightbox_new"));  
	    nvps.add(new BasicNameValuePair("LoginName", "15167101121"));  
	    nvps.add(new BasicNameValuePair("Password", "jia131415926"));  
	    nvps.add(new BasicNameValuePair("CheckCode", "2"));  
	    nvps.add(new BasicNameValuePair("bkurl", ""));  
	    nvps.add(new BasicNameValuePair("RemeberMe", "false"));  
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");//将表单参数转化为“实体”
		post.setEntity(entity); //将“实体“设置到POST请求里
		            
		HttpResponse response = client.execute(post);//提交POST请求
		HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
		String content = EntityUtils.toString(result);; //用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
		System.out.println(content);
		
		String memberpage = "http://i.zhaopin.com/resume/userinfo/add?type=0&iurl=&nname=";
		HttpGet httpget = new HttpGet(memberpage);
		response = client.execute(httpget);
		HttpEntity entitys =  response.getEntity();
		String html = EntityUtils.toString(entitys, "GBK");
		httpget.releaseConnection();

		System.out.println(html);
	}
	
	@Test
	public void login2ZhiLianAgain() throws ClientProtocolException, IOException {
		    Protocol https = new Protocol("https", new HTTPSSecureProtocolSocketFactory(), 443);
		    Protocol.registerProtocol("https", https);
		    
		    
		    HttpClient client = new DefaultHttpClient();                //构建一个Client
			HttpPost post = new HttpPost("https://passport.zhaopin.com/account/login");    //构建一个POST请求
			//构建表单参数
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		    //nvps.add(new BasicNameValuePair("layer_from", "wwwindex_rightbox_new"));  
		    nvps.add(new BasicNameValuePair("LoginName", "15167101121"));  
		    nvps.add(new BasicNameValuePair("Password", "jia131415926"));  
		    nvps.add(new BasicNameValuePair("CheckCode", "2"));  
		    nvps.add(new BasicNameValuePair("bkurl", ""));  
		    nvps.add(new BasicNameValuePair("RemeberMe", "false"));  
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nvps, "UTF-8");//将表单参数转化为“实体”
			post.setEntity(entity); //将“实体“设置到POST请求里
			            
			HttpResponse response = client.execute(post);//提交POST请求
			HttpEntity result = response.getEntity();//拿到返回的HttpResponse的"实体"
			String content = EntityUtils.toString(result);; //用httpcore.jar提供的工具类将"实体"转化为字符串打印到控制台
			System.out.println(content);
			
			String memberpage = "http://i.zhaopin.com/resume/userinfo/add?type=0&iurl=&nname=";
			HttpGet httpget = new HttpGet(memberpage);
			response = client.execute(httpget);
			HttpEntity entitys =  response.getEntity();
			String html = EntityUtils.toString(entitys, "GBK");
			httpget.releaseConnection();

			System.out.println(html);
			Protocol.unregisterProtocol("https");
		    
		

	}

}























