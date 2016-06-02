package javamail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//import com.yuson.j2ee.javamail.struts.form.EmailForm;
public class YusonJavaMail {
	private String from;
	private String to;
	private String subject;
	private String content;
	private String smtpServer;
	private String smtpAuth;
	private String smtpUsername;
	private String smtpPassword;

	public static void main(String[] args) {
		new YusonJavaMail().sendEmail();
	}

	public YusonJavaMail() {
		this.from = "m15167101121@163.com";
		this.to = "m15167101121@163.com";
		this.subject = "subject mail test";
		this.content = "content mail test";
		String temp = this.from.substring(this.from.indexOf("@") + 1,
				this.from.lastIndexOf(".com"));
		this.smtpServer = "smtp." + temp + ".com";
		this.smtpAuth = "true";
		this.smtpUsername = this.from.substring(0, this.from.indexOf("@"));
		/* Ofcourse, I execute the code with my email password, not "*" */
		this.smtpPassword = "*";
	}

	@SuppressWarnings("static-access")
	public boolean sendEmail() {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtpServer);
			properties.put("mail.smtp.auth", smtpAuth);
			Session session;
			if ("true".equals(smtpAuth)) {
				// smtp服务器需要验证，用YusonAuthertiactor来创建mail session
				YusonAuthenticator yusonAuthenticator = new YusonAuthenticator(
						smtpUsername, smtpPassword);
				session = Session.getInstance(properties, yusonAuthenticator);
			} else {
				session = Session.getInstance(properties);
			}
			// Debug
			session.setDebug(true);
			Message message = new MimeMessage(session);
			InternetAddress fromAddress = new InternetAddress(this.from);
			message.setFrom(fromAddress);
			InternetAddress toAddress = new InternetAddress(this.to);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			message.setSubject(this.subject);
			message.setText(this.content);
			message.setSentDate(new Date());
			message.saveChanges();
			Transport transport;
			transport = session.getTransport("smtp");
			transport.connect(this.smtpServer, this.smtpUsername,
					this.smtpPassword);
			transport.send(message, message.getAllRecipients());
			transport.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

class YusonAuthenticator extends Authenticator {
	private String username;
	private String password;

	public YusonAuthenticator(String username, String password) {
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}