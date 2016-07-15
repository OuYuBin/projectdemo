package singleton;

import org.junit.Test;

import cn.jpush.api.JPushClient;

public class NoStaticTest {
	
	private JPushClient jpushClient;

	public JPushClient getInstence() {
		if (jpushClient == null) {
			 jpushClient = new JPushClient("5194c89ed1587817671e3a81","c539ae2fb9bf518d09a98cd9", 3);
			//jpushClient = new JPushClient(appKey, masterSecret, 3);
		}
		return jpushClient;
	}

	@Test
	public void noStaticSingleton() {
		JPushClient jpushClient1=getInstence();
		System.out.println(jpushClient1);
		JPushClient jpushClient2=getInstence();
		System.out.println(jpushClient2);	
		
	}

}
