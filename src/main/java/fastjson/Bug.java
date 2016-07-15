package fastjson;

public class Bug<T> {
	private String name;
	private T animal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getAnimal() {
		return animal;
	}

	public void setAnimal(T animal) {
		this.animal = animal;
	}

}
