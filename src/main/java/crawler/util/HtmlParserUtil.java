package crawler.util;

import java.util.ArrayList;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.tags.Div;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.ObjectFindingVisitor;

public class HtmlParserUtil {

	public static List<Div> findDivByClass(String resource, String classVal)
			throws ParserException {
		List<Div> result = new ArrayList<Div>();
		Parser parser = new Parser(resource);
		ObjectFindingVisitor visitor = new ObjectFindingVisitor(Div.class);
		parser.visitAllNodesWith(visitor);
		Node[] nodes = visitor.getTags();
		for (int i = 0; i < nodes.length; i++) {
			Tag tag = (Tag) nodes[i];
			String attr = tag.getAttribute("class");
			if (attr != null && attr.equals(classVal)) {
				result.add((Div) tag);
			}
		}

		return result;
	}
}
