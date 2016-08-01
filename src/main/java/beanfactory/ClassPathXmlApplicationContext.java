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
			throws DocumentException, InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(this.getClass().getClassLoader()
				.getResourceAsStream(fileName));
		List<Element> elements = document.selectNodes("/beans/bean");
		for (Element e : elements) {
			String id = e.attributeValue("id");
			String value = e.attributeValue("class");
			// Class.forName()静态方法的目的是为了动态加载类。在加载完成后，一般还要调用Class下的newInstance()
			// 静态方法来实例化对象以便操作。因此，单单使用Class.forName()是动态加载类是没有用的，其最终目的是为了实例化对象。
			Object object = Class.forName(value).newInstance();
			beans.put(id, object);
		}
	}

	public Object getBean(String id) {
		return beans.get(id);
	}

}
