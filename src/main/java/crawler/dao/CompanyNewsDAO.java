package crawler.dao;
import java.util.List;

import crawler.news.CategoryNews;
import crawler.news.Company;


public interface CompanyNewsDAO {

	public List<Company> getCompanies();

	public List<Company> getChinaADRCompanies();

	public List<Company> getNoneChinaADRCompanies();

	public void saveCompanyNews(Company com);

	public void saveNews(CategoryNews news);

}
