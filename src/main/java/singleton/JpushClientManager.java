package singleton;

import org.junit.Test;

import cn.jpush.api.JPushClient;

public class JpushClientManager {

	private ThreadLocal<JPushClient> jpushClientLocal = new ThreadLocal<JPushClient>() {
		@Override
		protected JPushClient initialValue() {
			JPushClient jPushClient = new JPushClient(
					"5194c89ed1587817671e3a81", "c539ae2fb9bf518d09a98cd9");
			return jPushClient;
		}
	};

	private JPushClient getInstence() {
		return jpushClientLocal.get();
	}

	private void setInstence(JPushClient jpushClient) {
		jpushClientLocal.set(jpushClient);
	}

	@Test
	public void test() {

		System.out.println(getInstence());
		System.out.println(getInstence());

	}
	/*
	 * public static void main(String[] args) {
	 * System.out.println(JpushClientManager.getInstence());
	 * System.out.println(JpushClientManager.getInstence()); }
	 */
}
