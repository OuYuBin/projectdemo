package exchangeService;

import java.net.URI;

import org.apache.commons.mail.Email;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.core.service.item.Item;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.search.FindItemsResults;
import microsoft.exchange.webservices.data.search.ItemView;

public class ExchangeServiceTest {

	public static void main(String[] args) throws Exception {
		ExchangeService service = new ExchangeService(
				ExchangeVersion.Exchange2010);
		ExchangeCredentials credentials = new WebCredentials(
				"yuqiangjia", "Spring2016", "hengtiansoft.com");
		service.setCredentials(credentials);
		service.setUrl(new URI(
				"https://mail.hengtiansoft.com/ews/Exchange.asmx"));
		Folder folder = Folder.bind(service, WellKnownFolderName.Inbox);
		System.out.println("======" + folder.getDisplayName());
		ItemView view = new ItemView(10);
		FindItemsResults<Item> findItemsResults = service.findItems(
				folder.getId(), view);

		for (Item item : findItemsResults.getItems()) {
			EmailMessage message = EmailMessage.bind(service, item.getId());
			System.out.println("Sender -->"+message.getSender());
			System.out.println("Sub -->" + item.getSubject());
			item.load();
			System.out.println(" -->" + item.getBody());

		}

	}

}
