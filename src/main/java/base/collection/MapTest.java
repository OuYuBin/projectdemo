package base.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map map = new HashMap(7);
		map.put("1", "String");
		System.out.println(map.get("1"));
	}
}
