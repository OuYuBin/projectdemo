package clazz;

public class Parent {
	int i = 1;

	public Parent() {
		System.out.println("HAHA! I am Parent");
	}

	public Parent(String name) {
		System.out.println("HAHA! I am Parent,my name is " + name);
	}

	public void printI() {
		System.out.println("i=" + i);
	}

	public void method() {
		System.out.println("parent's method");
	}

}
