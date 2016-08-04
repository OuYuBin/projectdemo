package httpclientlogin.jushdto;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import cn.jpush.api.utils.Base64;

import com.alibaba.fastjson.JSON;

public class JpushTest {
	@Test
	public void jpush() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://api.jpush.cn/v3/schedules");
		httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
		httpPost.addHeader("Accept", "application/json");

		// appKey和masterSecret
		String appKey = null;
		String masterSecret = null;
		String auth = appKey + ":" + masterSecret;

		String base64_auth = new String(Base64.encode(auth.getBytes()));
		System.out.println("base64_auth:" + base64_auth);
		httpPost.addHeader("Authorization", base64_auth);

		// jpush参数
		ScheduleDto schedule = new ScheduleDto();
		schedule.setName("ScheduleTest");// 任务名(name)不可以有空格
		// schedule.setName("&#23450;时推送示例");
		schedule.setEnabled(true);
		TriggerDto trigger = new TriggerDto();
		SingleDto single = new SingleDto();
		single.setTime("2016-08-01 13:46:00");
		trigger.setSingle(single);
		schedule.setTrigger(trigger);
		PushDto push = new PushDto();
		push.setPlatform("all");
		push.setAudience("all");
		NotificationDto notification = new NotificationDto();
		notification.setAlert("5 minutes Schedule Test");
		push.setNotification(notification);
		MessageDto message = new MessageDto();
		message.setMsg_content("TEST");
		push.setMessage(message);
		OptionsDto options = new OptionsDto();
		options.setTime_to_live(30);
		push.setOptions(options);
		schedule.setPush(push);

		String jsonString = JSON.toJSONString(schedule);
		System.out.println("jsonString" + jsonString);

		StringEntity se = new StringEntity(jsonString);
		se.setContentType("text/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE,
				"application/json"));

		httpPost.setEntity(se);

		HttpResponse response = httpclient.execute(httpPost);
		HttpEntity entity = response.getEntity();

		// 在这里可以用Jsoup之类的工具对返回结果进行分析，以判断登录是否成功
		String postResult = EntityUtils.toString(entity, "GBK");

		System.out
				.println("=============================================================");
		System.out.println(postResult);

	}
}
