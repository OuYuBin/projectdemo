package crawler.news;
public class CategoryNews extends News {

	public static final String CATEGORY_RECOMMENDATION = "recommendation";

	public static final String CATEGORY_NEWS = "company_news";
	
	public static final String CATEGORY_PRESS_RELEASE = "press_release";

	private String category;
	
	private String source;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public boolean isValid() {
		return getUrl() != null && getTitle() != null;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
