package beanfactory;

import org.dom4j.DocumentException;

/*
 * 　 spring的IOC容器能够帮我们自动new对象，对象交给spring管之后我们不用自己手动去new对象了。那么它的原理是什么呢？是怎么实现的呢？
 * 下面我来简单的模拟一下spring的机制，相信看完之后就会对spring的原理有一定的了解。
 * 
 * spring使用BeanFactory来实例化、配置和管理对象，但是它只是一个接口，里面有一个getBean()方法。
 * 我们一般都不直接用BeanFactory，而是用它的实现类ApplicationContext，
 * 这个类会自动解析我们配置的applicationContext.xml，然后根据我们配置的bean来new对象，
 * 将new好的对象放进一个Map中，键就是我们bean的id,值就是new的对象。
 */
public class Test {
	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, DocumentException {

		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Object object = factory.getBean("c");
		Moveable moveable = (Moveable) object;
		moveable.run();
	}
}
