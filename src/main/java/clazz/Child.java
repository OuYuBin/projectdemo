package clazz;

public class Child extends Parent {
	int i = 3;

	public void test() {
		System.out.println("Child's method");
	}

	public void testThisAndSuper(String i) {
		System.out.println("this:" + this.i);
		System.out.println("super:" + super.i);
		super.method();
	}

	public static void main(String[] args) {
		String j = null;
		Child child = new Child();
		child.test();
		child.printI();
		child.testThisAndSuper(j);
		// System.out.println(this.i);
	}
}
