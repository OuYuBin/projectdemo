package crawler.news;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Company {

	private int msid;

	private String symbol;

	// avoid duplicated title
	private Map<String, CategoryNews> news = new HashMap<String, CategoryNews>();

	public int getMsid() {
		return msid;
	}

	public void setMsid(int msid) {
		this.msid = msid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<CategoryNews> getNews() {
		return new ArrayList<CategoryNews>(news.values());
	}

	public void addNews(CategoryNews news) {
		this.news.put(news.getTitle(), news);
	}

}
