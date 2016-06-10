package beanfactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ClassPathXmlApplicationContext implements BeanFactory {
	private Map<String, Object> beans = new HashMap<String, Object>();

	public ClassPathXmlApplicationContext(String fileName)
			throws DocumentException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(this.getClass().getClassLoader()
				.getResourceAsStream(fileName));
		List<Element> elements = document.selectNodes("/beans/bean");
		for(Element e:elements){
			String id=e.attributeValue("id");
			String value=e.attributeValue("class");
			Object object=Class.forName(value).newInstance();
			beans.put(id, object);
			
		}
	}

	public Object getBean(String id) {
		return beans.get(id);
	}

}
