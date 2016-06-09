package listandmap;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListAndMapTest {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Jack");
		list.add("Jack");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("===============");
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "jia");
		map.put(1, "jii");
		map.put(2, "iii");

		for (Integer k : map.keySet()) {
			System.out.println(k + map.get(k));
		}
	}

}
