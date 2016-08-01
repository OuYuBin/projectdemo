package threadlocal;

public class TestNUM {
	// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		public Integer initialValue() {
			return 0;
		}
	};

	// ②获取下一个序列值
	public int getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		TestNUM sn = new TestNUM();
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
		t1.start();
		t2.start();
		t3.start();

	}

	private static class TestClient extends Thread {
		private TestNUM sn;

		public TestClient(TestNUM sn) {
			this.sn = sn;
		}

		public void run() {
			// ④每个线程打出3个序列值
			for (int i = 0; i < 3; i++) {
				System.out.println("thread[" + Thread.currentThread().getName()
						+ "]-->sn[" + sn.getNextNum() + "]");
			}
		}
	}
}
