package jpush;


/**
 * Created by yuqiangjia on 2016/7/12
 */
public class AppPushMessageDto<T> {

	private T msg_content;
	private String title;

	public T getMsg_content() {
		return msg_content;
	}

	public void setMsg_content(T msg_content) {
		this.msg_content = msg_content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*
	 * public static void main(String[] args) { DriverAppPushCardMessageDto
	 * cardMessageDto = new DriverAppPushCardMessageDto( "15100001111",
	 * DriverLogType.ON_WORK); AppPushMessageDto<DriverAppPushCardMessageDto>
	 * messageDto = new AppPushMessageDto<>();
	 * messageDto.setMsg_content(cardMessageDto); messageDto.setTitle("UPDATE");
	 * String message = JSON.toJSONString(messageDto);
	 * 
	 * System.out.println(message); }
	 */

}
