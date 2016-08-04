package base.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		System.out.println(list.size());
		list.add("haha");
		list.add(null);

		System.out.println(list.get(0));
		System.out.println(list.get(1));

		List<String> list2 = new LinkedList<>();
		list2.add("linkedlist");
		System.out.println(list2.get(0));

		Vector<String> vector = new Vector<>();
		vector.add("1");
		System.out.println(vector.get(0));
	}
}
