package singleton;

public class Singleton {
	private static Singleton singleton;

	public static Singleton getInstence() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}
