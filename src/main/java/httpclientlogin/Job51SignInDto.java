package httpclientlogin;

import java.io.Serializable;

public class Job51SignInDto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private String userName;

	private String userPwd;

	private boolean autoLogin;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}
	
	
}
