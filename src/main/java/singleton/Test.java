package singleton;

public class Test {
	@org.junit.Test
	public void test() {
		Singleton singleton1= Singleton.getInstence();
		Singleton singleton2=Singleton.getInstence();
		System.out.println(singleton1);
		System.out.println(singleton2);
	}

}
