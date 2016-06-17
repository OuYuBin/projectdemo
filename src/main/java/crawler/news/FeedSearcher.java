package crawler.news;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import crawler.util.TimeUtil;
import crawler.util.UrlUtil;


public abstract class FeedSearcher {

	public List<RssItem> search(String url) {
		SAXReader reader = new SAXReader();
		List<RssItem> result = new ArrayList<RssItem>();
		Document doc;
		try {
			doc = reader.read(UrlUtil.getInputStream(url));
		} catch (DocumentException e) {
			e.printStackTrace();
			System.err.println("Failed to read feed: " + url);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Failed to read feed: " + url);
			return result;
		}

		System.out.println("Feed: " + url);
		List<Element> elemets = doc.selectNodes("//item");
		for (int i = 0; i < elemets.size(); i++) {
			Element element = (Element) elemets.get(i);
			result.add(parseElement(element));
		}

		return result;
	}

	protected void parseBasicElement(Element element, RssItem rssItem) {
		String title = element.elementText("title");
		String link = element.elementText("link");
		String pubDate = element.elementText("pubDate");
		long time;
		try {
			time = TimeUtil.convertToTime(pubDate);
		} catch (ParseException e) {
			e.printStackTrace();
			System.err.println("Failed to convert pubDate: " + pubDate);
			time = new Date().getTime();
		}

		rssItem.setUrl(link);
		rssItem.setTitle(title);
		rssItem.setTime(time);
	}

	protected abstract RssItem parseElement(Element element);
}
