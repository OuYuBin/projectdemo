package fastjson;

import com.alibaba.fastjson.JSON;

public class JSONParse {
	public static void main(String[] args) {

		Cat cat = new Cat();
		cat.setCatname("HelloKittey");
		cat.setCatage("22");

		Dog dog = new Dog();
		dog.setName("HotDog");
		dog.setCat(cat);
		String message = JSON.toJSONString(dog);
		System.out.println("dog:" + message);

		Dog dog2 = JSON.parseObject(message, Dog.class);
		System.out.println(dog2.getName());
		System.out.println(dog2.getCat().getCatage());
		System.out.println(dog2.getCat().getCatname());
		System.out.println("============================================");

		Animal<Cat> animal = new Animal<>();
		animal.setCat(cat);
		animal.setName("Animal Test");
		String ss = JSON.toJSONString(animal);
		System.out.println("animal:" + ss);

		Dog dog3 = JSON.parseObject(ss, Dog.class);
		System.out.println("dog3:" + dog3.getName());
		System.out.println("dog3:" + dog3.getCat().getCatname());
		System.out.println("dog3:" + dog3.getCat().getCatage());

		System.out.println("=================BUG======================");
		Bug<Cat> bug = new Bug<>();
		bug.setAnimal(cat);
		bug.setName("bug");
		String sss = JSON.toJSONString(bug);
		System.out.println("bug:" + sss);
		Bug<Cat> bug2 = JSON.parseObject(sss, Bug.class);
		System.out.println("bug2:" + bug2.getName());
		System.out.println("bug2:" + bug2.getAnimal().getCatage());
		System.out.println("bug2:" + bug2.getAnimal().getCatname());

	}
}
