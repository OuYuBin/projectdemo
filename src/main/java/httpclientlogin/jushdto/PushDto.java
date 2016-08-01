package httpclientlogin.jushdto;

public class PushDto {
	private String platform;
	private String audience;
	private NotificationDto notification;
	private MessageDto message;
	private OptionsDto options;

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public NotificationDto getNotification() {
		return notification;
	}

	public void setNotification(NotificationDto notification) {
		this.notification = notification;
	}

	public MessageDto getMessage() {
		return message;
	}

	public void setMessage(MessageDto message) {
		this.message = message;
	}

	public OptionsDto getOptions() {
		return options;
	}

	public void setOptions(OptionsDto options) {
		this.options = options;
	}

}
