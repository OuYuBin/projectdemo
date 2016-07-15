package fastjson;

public class Animal<T> {

	private String name;
	private T cat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public T getCat() {
		return cat;
	}

	public void setCat(T cat) {
		this.cat = cat;
	}

}
