package jpush;

public class PushMessageRequest {

	private String tags;

	private String regIds;

	private String message;

	private String notification;

	private String title;

	private String alias;

	private String extra;

	public PushMessageRequest() {
		super();
	}

	public PushMessageRequest(String message, String title, String alias) {
		super();
		this.message = message;
		this.title = title;
		this.alias = alias;
		this.notification = "";
	}

	public PushMessageRequest(String tags, String regIds, String message,
			String title, String alias, String extra) {
		super();
		this.tags = tags;
		this.regIds = regIds;
		this.message = message;
		this.title = title;
		this.alias = alias;
		this.notification = "";
		this.extra = extra;
	}

	public PushMessageRequest(String tags, String regIds, String message,
			String notification, String title, String alias, String extra) {
		super();
		this.tags = tags;
		this.regIds = regIds;
		this.message = message;
		this.notification = notification;
		this.title = title;
		this.alias = alias;
		this.extra = extra;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getRegIds() {
		return regIds;
	}

	public void setRegIds(String regIds) {
		this.regIds = regIds;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

}
