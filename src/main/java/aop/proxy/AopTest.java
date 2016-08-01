package aop.proxy;

import aop.Hello;
import aop.IHello;

public class AopTest {
	public static void main(String[] args) {
		IHello hello = (IHello) new DynaProxyHello().bind(new Hello());
		hello.sayHello("wallet white");
		System.out.println("------------------");
		hello.sayGoodBye("wallet white");
	}
}
