package httpclientlogin;

import java.io.Serializable;

/**
 * Class Name: SignInDto Description: TODO
 * 
 * @author SC
 *
 */
public class SignInDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String userName;

	private String password;

	private boolean rememberMe;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
}
