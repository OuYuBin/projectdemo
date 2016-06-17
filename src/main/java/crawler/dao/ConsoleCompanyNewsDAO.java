package crawler.dao;

import java.util.ArrayList;
import java.util.List;

import crawler.news.CategoryNews;
import crawler.news.Company;

public class ConsoleCompanyNewsDAO implements CompanyNewsDAO {

	@Override
	public List<Company> getCompanies() {
		return getCompanies("AA");
	}

	@Override
	public List<Company> getChinaADRCompanies() {
		return getCompanies("BIDU");
	}

	@Override
	public void saveCompanyNews(Company com) {
		for (CategoryNews n : com.getNews()) {
			System.out.println(n.getTitle() + n.getUrl());
		}
	}

	@Override
	public List<Company> getNoneChinaADRCompanies() {
		return getCompanies("APPL");
	}

	private List<Company> getCompanies(String symbol) {
		List<Company> coms = new ArrayList<Company>();
		Company com = new Company();
		com.setSymbol(symbol);
		coms.add(com);
		return coms;
	}

	@Override
	public void saveNews(CategoryNews news) {
		System.out.println(news.getTitle() + news.getUrl());
	}

}
