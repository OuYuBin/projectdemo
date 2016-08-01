package jpush;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.alibaba.fastjson.JSON;

/**
 * Created by yuqiangjia on 2016年7月11日
 */
public class Jpush {
	public static void main(String[] args) throws APIConnectionException {
		DriverAppPushCardMessageDto cardMessageDto = new DriverAppPushCardMessageDto(
				"HAHAHA", "ON", "VOICE");
		// String message = JSON.toJSONString(cardMessageDto);
		/*
		 * AppPushMessageDto<DriverAppPushCardMessageDto> messageDto = new
		 * AppPushMessageDto<>(); // AppPushMessageDto<String> messageDto = new
		 * AppPushMessageDto<>(); messageDto.setMsg_content(cardMessageDto); //
		 * messageDto.setTitle("UPDATE"); messageDto.setTitle("VOICE");
		 */
		String message = JSON.toJSONString(cardMessageDto);

		String string = "通知";

		/*
		 * PushMessageRequest pushRequest = new PushMessageRequest("", "", "",
		 * string, "", "15605695605;15868855446", message);// 通知
		 */
		PushMessageRequest pushRequest = new PushMessageRequest(message,
				"乘客APP", "15868855446");// 消息15605695605;
		// PushMessageRequest(String tags, String regIds, String message, String
		// notification, String title, String alias, String extra)

		pushMessage(pushRequest);
		// PushPayload payload=buildPushObject_all_all_alert("7.12");

	}

	// 构建简单的推送对象：向所有平台，所有人，推送内容为 content 的通知
	public static PushPayload buildPushObject_all_all_alert(String content) {
		return PushPayload.alertAll(content);
	}

	// 构建推送对象：所有平台，推送目标是别名为alias，通知内容为 content。
	public static PushPayload buildPushObject_all_alias_alert(String alias,
			String content) {
		return PushPayload.newBuilder().setPlatform(Platform.all())// 所有平台
				.setAudience(Audience.alias(alias))// 向选定的人推送
				.setNotification(Notification.alert(content)).build();// 消息内容
	}

	// 　构建推送对象：向android_ios平台，向目标标签tag，通知标题title，内容为 content
	public static PushPayload buildPushObject_android_tag_alertWithTitle(
			String tag, String title, String content) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios())
				.setAudience(Audience.tag(tag))
				.setNotification(Notification.android(content, title, null))
				.build();
	}

	/**
	 * @Description: 通过别名发送jpush通知
	 * @param content
	 * @param alias
	 * @param title
	 * @return PushPayload
	 */
	public static PushPayload buildPushObject_all_alias_alert(String content,
			String title, String... alias) {
		return PushPayload
				.newBuilder()
				.setPlatform(Platform.android_ios())
				.setAudience(Audience.alias(alias))
				.setNotification(
						Notification
								.newBuilder()
								.addPlatformNotification(
										IosNotification.newBuilder()
												.setAlert(content).autoBadge()
												.setSound("happy.caf").build())
								.addPlatformNotification(
										AndroidNotification.newBuilder()
												.setTitle(title)
												.setAlert(content).build())
								.build())
				.setOptions(
						Options.newBuilder().setTimeToLive(86400)
								.setBigPushDuration(5).setApnsProduction(true)
								.build()).build();
	}

	public static boolean pushMessage(PushMessageRequest request)
			throws APIConnectionException {

		Map<String, Integer> errorMap = new HashMap<String, Integer>();

		JPushClient jPushClient = new JPushClient("c539ae2fb9bf518d09a98cd9",
				"5194c89ed1587817671e3a81", 3);

		Builder builder = PushPayload.newBuilder().setPlatform(
				Platform.android_ios());
		/*
		 * Options options = Options.newBuilder().setTimeToLive(86400)
		 * .setApnsProduction(true).build();// 保存离线消息
		 * builder.setOptions(options);
		 */
		if (request.getNotification() != null
				&& request.getNotification() != "") {
			// Notification notification =
			// Notification.alert(request.getNotification());
			Notification notification = Notification
					.newBuilder()
					.addPlatformNotification(
							IosNotification.newBuilder()
									.setAlert(request.getNotification())
									// .addExtra("Extra", request.getExtra())
									/*
									 * .addExtra("msg_content", "123123")
									 * .addExtra("title", "VOICE")
									 */.build())
					.addPlatformNotification(
							AndroidNotification.newBuilder()
									.setTitle(request.getTitle())
									.setAlert(request.getNotification())
									// .addExtra("Extra", request.getExtra())
									.build()).build();
			builder.setNotification(notification);
		}
		if (request.getMessage() != null && request.getMessage() != "") {
			Message message = Message.newBuilder()
					.setMsgContent(request.getMessage())
					// .addExtra("Extra", request.getExtra())
					/*
					 * .addExtra("msg_content", "123123") .addExtra("title",
					 * "VOICE")
					 */
					.setTitle(request.getTitle()).build();
			builder.setMessage(message);
		}

		if ((request.getTags() == null || request.getTags().isEmpty())
				&& (request.getRegIds() == null || request.getRegIds()
						.isEmpty())
				&& (request.getAlias() == null || request.getAlias().isEmpty())) {
			try {
				builder.setAudience(Audience.all());
				jPushClient.sendPush(builder.build());
			} catch (APIRequestException e) {
				errorMap.put("all", e.getErrorCode());
				e.getErrorCode();
			}
		} else {
			if (null != request.getTags()
					&& !request.getTags().trim().isEmpty()) {
				String[] tags = request.getTags().trim().split(";");
				for (int i = 0; i < tags.length; i++) {
					try {
						builder.setAudience(Audience.tag(tags[i]));
						jPushClient.sendPush(builder.build());
					} catch (APIRequestException e) {
						errorMap.put(tags[i], e.getErrorCode());
						e.getErrorCode();
					}
				}
			}
			if (null != request.getRegIds()
					&& !request.getRegIds().trim().isEmpty()) {
				String[] regIds = request.getRegIds().trim().split(";");

				for (int i = 0; i < regIds.length; i++) {
					try {
						builder.setAudience(Audience.registrationId(regIds[i]));
						jPushClient.sendPush(builder.build());
					} catch (APIRequestException e) {
						errorMap.put(regIds[i], e.getErrorCode());
						e.getErrorCode();
					}
				}
			}
			if (null != request.getAlias()
					&& !request.getAlias().trim().isEmpty()) {
				String[] alias = request.getAlias().trim().split(";");

				for (int i = 0; i < alias.length; i++) {
					try {
						builder.setAudience(Audience.alias(alias[i]));
						jPushClient.sendPush(builder.build());
					} catch (APIRequestException e) {
						errorMap.put(alias[i], e.getErrorCode());
						e.getErrorCode();
					}
				}
			}
		}

		return true;
	}
}
