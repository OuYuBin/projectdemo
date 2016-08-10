package base.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(7);
		map.put("1", "String");
		System.out.println(map.get("1"));

		Map<String, String> map2 = new Hashtable<>();
	}
}
