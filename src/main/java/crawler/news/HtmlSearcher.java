package crawler.news;

import java.text.ParseException;
import java.util.Date;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import crawler.util.TimeUtil;


public abstract class HtmlSearcher {

	protected void setNewsContent(Node node, News news) {
		NodeList children = node.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Node child = children.elementAt(i);
			if (child instanceof Tag) {
				LinkTag a = null;
				if (isTime((Tag) child)) {
					news.setTime(getTime(child.toHtml()));
				} else if ((a = getLinkTag(child)) != null) {
					news.setTitle(a.getLinkText());
					news.setUrl(a.getLink());
				}
			}
		}
	}

	protected LinkTag getLinkTag(Node node) {
		if (node instanceof LinkTag) {
			return (LinkTag) node;
		}

		return null;
	}

	private long getTime(String html) {
		String date = getTimeText(html);
		try {
			return TimeUtil.convertToTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date().getTime();
		}
	}

	protected abstract boolean isTime(Tag child);

	protected abstract String getTimeText(String html);

}
