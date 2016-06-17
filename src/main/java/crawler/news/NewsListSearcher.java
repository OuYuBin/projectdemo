package crawler.news;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Tag;
import org.htmlparser.tags.Bullet;
import org.htmlparser.tags.BulletList;
import org.htmlparser.tags.Div;
import org.htmlparser.tags.Span;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import crawler.util.HtmlParserUtil;
import crawler.util.UrlUtil;


public abstract class NewsListSearcher extends HtmlSearcher {

	public List<RssItem> search(String url) {
		List<RssItem> result = new ArrayList<RssItem>();
		List<BulletList> newsLists;
		try {
			newsLists = getNewsLists(url);
		} catch (ParserException e) {
			e.printStackTrace();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
			return result;
		}

		for (BulletList newsList : newsLists) {
			Node[] nodes = newsList.getChildrenAsNodeArray();
			for (int i = 0; i < nodes.length; i++) {
				if (nodes[i] instanceof Bullet) {
					result.add(parseToNews(nodes[i]));
				}
			}
		}

		return result;
	}

	private List<BulletList> getNewsLists(String url) throws ParserException,
			IOException {
		List<BulletList> bls = new ArrayList<BulletList>();
		List<Div> divs = getNewsDiv(url);
		if (divs.size() == 0) {
			System.err.println("Failed to find news div: " + url);
			return bls;
		}

		Div div = divs.get(0);
		NodeList children = div.getChildren();
		for (int i = 0; i < children.size(); i++) {
			Node child = children.elementAt(i);
			if (children.elementAt(i) instanceof BulletList) {
				bls.add((BulletList) child);
			}
		}

		return bls;
	}

	private List<Div> getNewsDiv(String url) throws ParserException, IOException {
		return HtmlParserUtil.findDivByClass(UrlUtil.getUrlContent(url),
				getDivClass());
	}

	@Override
	protected boolean isTime(Tag child) {
		return child instanceof Span;
	}

	protected abstract News parseToNews(Node node);

	protected abstract String getDivClass();

}
