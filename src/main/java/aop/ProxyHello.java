package aop;

public class ProxyHello implements IHello {
	private IHello hello;

	public ProxyHello(IHello hello) {
		this.hello = hello;
	}

	@Override
	public void sayHello(String name) {
		Logger.logging(Level.DEBUG, "sayHello method start");
		hello.sayHello(name);
		Logger.logging(Level.INFO, "sayHello method end");
	}

	@Override
	public void sayGoodBye(String name) {
		hello.sayGoodBye(name);
	}

}
