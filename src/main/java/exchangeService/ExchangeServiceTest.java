package exchangeService;

import java.net.URI;
import java.util.Iterator;

import javamail.SendMailUtil;

import org.apache.commons.mail.Email;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.enumeration.service.DeleteMode;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

public class ExchangeServiceTest {

	public static void main(String[] args) throws Exception {
		ExchangeService service = new ExchangeService(
				ExchangeVersion.Exchange2010);//新建server版本
		ExchangeCredentials credentials = new WebCredentials(
				"yuqiangjia", "Spring2016", "hengtiansoft.com");
		service.setCredentials(credentials);
		service.setUrl(new URI(
				"https://mail.hengtiansoft.com/ews/Exchange.asmx"));
		Folder folder = Folder.bind(service, WellKnownFolderName.Inbox);
		System.out.println("======" + folder.getDisplayName());
		int count=folder.getTotalCount();//Inbox 邮件总数
		System.out.println("Inbox count -->"+count);
		int unReadCount=folder.getUnreadCount();//Inbox 未读邮件总数
		System.out.println("InboxUnReadCount -->"+unReadCount);
		ItemView view = new ItemView(1);
		FindItemsResults<Item> findItemsResults = service.findItems(
				folder.getId(), view);

		for (Item item : findItemsResults.getItems()) {
			EmailMessage message = EmailMessage.bind(service, item.getId());
			System.out.println("Sender -->"+message.getSender());
			//System.out.println("Message -->"+message.getBody());
			System.out.println("IsRead -->"+message.getIsRead());
			System.out.println("DateTimeSent -->"+message.getDateTimeSent());
		    //message.delete(DeleteMode.MoveToDeletedItems);//删除到垃圾箱
			System.out.println("Sub -->" + item.getSubject());
			
			//item.load();
			//System.out.println(" -->" + item.getBody());

		}
		
		//sendMail(service);
		

	}
	
	public static void sendMail(ExchangeService service) throws Exception{
		 EmailMessage msg=new EmailMessage(service);
		 msg.setSubject("From Java");//主题
		 msg.setBody(MessageBody.getMessageBodyFromText("Sent using the EWS Managed API."));//内容
		 msg.getToRecipients().add("yuqiangjia@hengtiansoft.com");//发件人
		 msg.getCcRecipients().add("yuqiangjia@hengtiansoft.com");//抄送人
		 msg.send();
	}

}




































