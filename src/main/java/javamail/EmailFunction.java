package javamail;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * javaMail API 模拟邮箱发送电子邮件
 * @author liuyt
 * @date  2014-10-24 上午10:10:51
 */
public class EmailFunction {
    /**
     * 发送邮件的props文件 (可使用自建priperty文件)
     * 用于初始化一个session实例，配置了一个session会话的一些基本信息
     */
    private final transient Properties props = new Properties();
    
    /**
     * 邮件发送者的用户名和密码
     */
    //private transient String username = "m15167101121@163.com";
    private transient String username = "1945227880@qq.com";
    private transient String password = "131415926";

    /**
     * session为一个基本的邮件会话，通过该会话可执行其他邮件工作
     * 如：之后的初始化一个MimeMessage实例
     */
    private transient Session session;
    
    /**
     * MIME类型邮件MimeMessage类（抽象消息类Message的一个子类）
     * 可以通过将Session对象传递给MimeMessage构造器的方法来创建
     */
    private transient MimeMessage message;
    
    /**
     * 邮件内容类型 （这里演示一个html格式的消息格式）
     */
    private final static String CONTENT_TYPE_HTML = "text/html;charset=utf-8";
    
    /**
     * 端口号 
     */
    private final static int MAIL_PORT = 25;
    
    /**
     * 邮件内容
     */
    private String content = "点击进入» <a href='http://www.cnblogs.com/liuyitian'>刘一天的博客</a>";
    
     /** 
     * 继承Authenticator子类用于用户认证  （这里指邮件服务器对用户的认证）
     * 也可外部创建一个单独的邮件实体类（包涵用户名/密码即可），继承Authenticator类来重写PasswordAuthentication方法
     */  
    static class MyAuthenricator extends Authenticator{  
        private String user=null;  
        private String pass="";  
        public MyAuthenricator(String user,String pass){  
            this.user=user;  
            this.pass=pass;  
        }  
        @Override  
        protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(user,pass);  
        }  
          
    }  
    
    /**
     * 初始化    session 实例方法
     * @param username 发送邮件的用户名(地址)
     * @param password 密码
     * @param smtpHostName  SMTP邮件服务器地址
     */
    private void initSession(String username, String password, String smtpHostName) {
        
        // 初始化props文件
        props.setProperty("mail.transport.protocol", "smtp");//发送邮件协议
        props.put("mail.smtp.auth", "true");        //需要验证
        props.put("mail.smtp.host", smtpHostName);    //服务器地址  
        
        // 根据property文件创建session,并传入Authenticator进行验证
        session = Session.getInstance(props, new MyAuthenricator(username, password));
        
        // 是否控制台打印消息列表 （可选）
        session.setDebug(true);
    }
    
    /**
     * 初始化邮箱message（消息实例）方法
     * @param subject    邮件主题
     * @param content    邮件内容
     * @throws MessagingException 
     * @throws AddressException 
     * @throws UnsupportedEncodingException 
     */
    private void initMessage(String subject, Object content) 
            throws AddressException, MessagingException, UnsupportedEncodingException {
        
        // 根据session创建一个消息对象
        message = new MimeMessage(session);
        
        // 设置发件人地址  (第二个参数为显示发件人名称，目前还没有测试成功)
        message.setFrom(new InternetAddress(username, "要显示的发件人名称"));
        
        // 设置邮件的主题
        message.setSubject("主题：javamail测试邮件");
        
        // 设置邮件的发送内容和内容的content-type（这里采用text/html格式）
        message.setContent(content.toString(),EmailFunction.CONTENT_TYPE_HTML);
        
        // 设置邮件的接收人地址--方法一 (选其一即可)
        // Address[] address = new Address[]{new InternetAddress("418874847@qq.com"),"...更多列表..."};
        // message.setReplyTo(addresses);
        
        // 设置邮件的接收人地址--方法二 (选其一即可)
        // 如果群发邮件，收件人较多，可另写一个方法用于专门循环遍历并设置接收人
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("yuqiangjia@hengtiansoft.com"));
       // message.addRecipient(Message.RecipientType.TO, new InternetAddress("12450374@qq.com"));
    }
    
    /**
     * 初始化邮件发送器方法
     * @param username  发送邮件的用户名(地址)，并以此解析SMTP服务器地址
     * @param password  发送邮件的密码
     * @throws MessagingException 
     * @throws UnsupportedEncodingException 
     * @throws AddressException 
     * @return
     */
    public void SimpleMailSender(final String username, final String password) 
            throws AddressException, UnsupportedEncodingException, MessagingException {
        
        // 通过邮箱地址解析出smtp服务器，对大多数邮箱都管用 (还有IMAP和POP3)
        final String smtpHostName = "smtp." + username.split("@")[1];
        
        // 调用初始化session方法
        initSession(username, password, smtpHostName);
        
        // 调用初始化MimeMessage方法 （在初始化session完毕后）
        initMessage("邮件主题：测试邮件", content);
    }
    
    /**
     * 邮件发送
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    private void send() throws MessagingException, UnsupportedEncodingException {
        
        // 调用初始化邮件方法
        SimpleMailSender(username, password);

        // 根据session来获得一个Transport抽象类对象
        Transport tran = session.getTransport();
        
        // 打开链接 ,此时会去校验用户名和密码 (参数列表：【 邮箱服务器地址】【端口号】【 发件箱用户名】【发件箱密码】)
        tran.connect(props.getProperty("mail.smtp.host"), EmailFunction.MAIL_PORT, username, password);
        
        // 发送邮件  (第二个参数null指收件人地址，因为在初始化message时已经设置好了收件人地址，此处便省略)
        tran.sendMessage(message, null);
        
        // 关闭通道
        tran.close();
    }
    
    /**
     * main方法测试
     * @param args
     * @throws AddressException
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */ 
    public static void main(String[] args) 
            throws AddressException, MessagingException, UnsupportedEncodingException {
        //直接调用发送方法
        new EmailFunction().send();
    }
    
}