package jpush;

import org.junit.Test;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class MyJpush {
	private static final String appKey = "5194c89ed1587817671e3a81";
	private static final String masterSecret = "c539ae2fb9bf518d09a98cd9";

	private JPushClient jpushClient;

	public JPushClient getInstence() {
		if (jpushClient == null) {
			jpushClient = new JPushClient(masterSecret, appKey);
		}
		return jpushClient;
	}

	/**
	 * @Description: 广播，推送对象：向所有平台，所有人，推送内容为 content 的通知
	 * @param content
	 * @return PushPayload
	 */
	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

	/**
	 * @Description: JPush通知，推送对象：android_ios平台，推送目标是别名为alias
	 * @param alias
	 * @param title
	 * @param content
	 * @param extra
	 * @return PushPayload
	 */
	public static PushPayload buildPushNotification_all_alias_alertWithTitleAndExtra(
			String alias, String title, String content, String extra) {
		return PushPayload
				.newBuilder()
				// android_ios平台
				.setPlatform(Platform.android_ios())
				// 向选定的人推送
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(content)
												.addExtra("extra", extra)
												.build())
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setAlert(content)
												.setTitle(title)
												.addExtra("extra", extra)
												.build()).build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();// 消息内容
	}

	/**
	 * @Description: JPush通知，推送对象：向android_ios平台，向目标标签tag
	 * @param tag
	 * @param title
	 * @param content
	 * @param extra
	 * @return PushPayload
	 */
	public static PushPayload buildPushNotification_android_ios_tag_alertWithTitleAndExtra(
			String tag, String title, String content, String extra) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag(tag))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(content)
												.addExtra("extra", extra)
												.build())
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setAlert(content)
												.setTitle(title)
												.addExtra("extra", extra)
												.build()).build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	/**
	 * @Description: JPush消息，推送对象：android_ios平台，推送目标是别名为alias
	 * @param alias
	 * @param title
	 * @param content
	 * @param extra
	 * @return PushPayload
	 */
	public static PushPayload buildPushMessage_all_alias_alertWithTitleAndExtra(
			String alias, String title, String content, String extra) {
		return PushPayload
				.newBuilder()
				// android_ios平台
				.setPlatform(Platform.android_ios())
				// 向选定的人推送
				.setAudience(Audience.alias(alias))
				// 消息内容
				.setMessage(
						Message.newBuilder().setMsgContent(content)
								.setTitle(title).addExtra("extra", extra)
								.build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	/**
	 * @Description: JPush消息，推送对象：向android_ios平台，向目标标签tag
	 * @param tag
	 * @param title
	 * @param content
	 * @param extra
	 * @return PushPayload
	 */
	public static PushPayload buildPushMessage_android_ios_tag_alertWithTitleAndExtra(
			String tag, String title, String content, String extra) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.tag(tag))
				.setMessage(
						Message.newBuilder().setMsgContent(content)
								.setTitle(title).addExtra("extra", extra)
								.build())
				.setOptions(
						Options.newBuilder().setApnsProduction(true).build())
				.build();
	}

	/**
	 * @Description: 根据别名发送消息(传入的参数固定)
	 * @param alias
	 * @param msgContent
	 * @param title
	 * @param username
	 * @param driverLogType
	 * @param pushMessageType
	 * @return PushPayload
	 */

	public static PushPayload buildPushObject_android_ios_alias_alertWithTitleExtra(
			String alias, String msgContent, String title, String username,
			String driverLogType, String pushMessageType) {
		return PushPayload.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(alias))
				// 消息内容
				.setMessage(
						Message.newBuilder().setMsgContent(msgContent)
								.addExtra("username", username)
								.addExtra("driverLogType", driverLogType)
								.addExtra("pushMessageType", pushMessageType)
								.setTitle(title).build()).build();
	}

	@Test
	public void test() {
		PushPayload payload = buildPushObject_android_ios_alias_alertWithTitleExtra(
				"15868855446", "疯了", "乘客APP", "15068722489", "ON", "UPDATE");
		JPushClient jpushClient = getInstence();
		try {
			jpushClient.sendPush(payload);
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (APIRequestException e) {
			e.printStackTrace();
		}
	}
}
