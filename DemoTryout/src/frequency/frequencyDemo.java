package frequency;

import java.util.HashMap;
import java.util.Map;


public class frequencyDemo {

	public static void main(String[] args) {
		
		String s1 = "irresponsible";
		Map<Character, Integer> myMap = new HashMap<Character, Integer>();
		for (Character element : s1.toCharArray()) { 
			if (myMap.containsKey(element)) {
				myMap.replace(element, myMap.get(element), myMap.get(element) + 1);
			} else {
				myMap.put(element, 1);
			}
		}
		System.out.println(myMap); 
		System.out.println("duplicate occurences are :-");
		myMap.entrySet().forEach(entry -> {
			if (entry.getValue() == 2) {
				System.out.print(entry.getKey().toString()+" "); 
			}
		});
	}

}
