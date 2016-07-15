package jpush;

/**
 * Created by yuqiangjia on 2016/7/7
 */
public class DriverAppPushCardMessageDto {

	private String username;
	private String driverLogType;
	private String pushMessageType;

	public DriverAppPushCardMessageDto(String username, String driverLogType,
			String pushMessageType) {
		this.username = username;
		this.driverLogType = driverLogType;
		this.pushMessageType = pushMessageType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDriverLogType() {
		return driverLogType;
	}

	public void setDriverLogType(String driverLogType) {
		this.driverLogType = driverLogType;
	}

	public String getPushMessageType() {
		return pushMessageType;
	}

	public void setPushMessageType(String pushMessageType) {
		this.pushMessageType = pushMessageType;
	}

}
