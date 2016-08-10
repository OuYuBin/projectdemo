package base.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(7);
		map.put("1", "String");
		map.put("1", "String1");
		map.put(null, "niha");
		System.out.println(map.get("1"));
		System.out.println(map.get(0));
		System.out.println(map.get(null));

		Map<String, String> map2 = new Hashtable<>();
		// map2.put(null, "ah");

		System.out.println(map2.hashCode());

		Integer a = 10;
		System.out.println(a.hashCode());

		String ss = "shi";
		System.out.println(ss.hashCode());

	}
}
