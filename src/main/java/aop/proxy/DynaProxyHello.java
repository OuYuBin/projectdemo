package aop.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import aop.Level;
import aop.Logger;

/**
 * Hello动态代理类 代理对象与被代理对像解藕
 */
public class DynaProxyHello implements InvocationHandler {
	/*
	 * 要处理的对象
	 */
	private Object delegate;

	/**
	 * 动态生成方法被处理过后的对象(写法固定)
	 * 
	 * @param delegate
	 * @return Object
	 */
	public Object bind(Object delegate) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(
				this.delegate.getClass().getClassLoader(), this.delegate
						.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object result = null;
		// 执行原来的方法之前记录日志
		Logger.logging(Level.DEBUG, method.getName() + " method start");

		// JVM通过这条语句执行原来的方法(反射机制)
		result = method.invoke(this.delegate, args);

		// 执行原来的方法之后记录日志
		Logger.logging(Level.INFO, method.getName() + " method end");
		return null;
	}
}
