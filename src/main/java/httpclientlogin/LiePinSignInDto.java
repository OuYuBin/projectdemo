package httpclientlogin;

import java.io.Serializable;


public class LiePinSignInDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	private String layer_from;

	private String user_login;

	private String user_pwd;

	private String chk_remember_pwd;	

	public String getLayer_from() {
		return layer_from;
	}

	public void setLayer_from(String layer_from) {
		this.layer_from = layer_from;
	}

	public String getUser_login() {
		return user_login;
	}

	public void setUser_login(String user_login) {
		this.user_login = user_login;
	}

	public String getUser_pwd() {
		return user_pwd;
	}

	public void setUser_pwd(String user_pwd) {
		this.user_pwd = user_pwd;
	}

	public String isChk_remember_pwd() {
		return chk_remember_pwd;
	}

	public void setChk_remember_pwd(String chk_remember_pwd) {
		this.chk_remember_pwd = chk_remember_pwd;
	}

	
	
}
