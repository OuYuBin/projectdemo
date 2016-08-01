package aop;

public class AopTest {
	public static void main(String[] args) {

		// 无日志记录功能
		IHello hello1 = new Hello();
		hello1.sayHello("wallet white");

		// 有日志记录功能
		IHello hello2 = new ProxyHello(new Hello());
		System.out.println("------------------------------");
		hello2.sayHello("wallet white");
	}
}
