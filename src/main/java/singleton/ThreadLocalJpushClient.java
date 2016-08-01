package singleton;

import org.junit.Test;

import cn.jpush.api.JPushClient;

public class ThreadLocalJpushClient {
	private ThreadLocal<JPushClient> jpushClientLocal = new ThreadLocal<>();

	public JPushClient getInstence() {
		if (jpushClientLocal.get() == null) {
			JPushClient jpushClient = new JPushClient(
					"5194c89ed1587817671e3a81", "c539ae2fb9bf518d09a98cd9");
			jpushClientLocal.set(jpushClient);
			return jpushClient;
		} else {
			return jpushClientLocal.get();
		}
	}

	@Test
	public void test() {
		JPushClient jpushClient1 = getInstence();
		System.out.println(jpushClient1);
		JPushClient jpushClient2 = getInstence();
		System.out.println(jpushClient2);

	}
}
