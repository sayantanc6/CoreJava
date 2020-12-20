package hackerearth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Permutation {

	public static void main(String[] args) {
		String s1 = "MATHEMATICS";
		char[] letters = s1.toCharArray();
		Map<Character, Integer> myMap = new HashMap<>();
		int n = letters.length;
		int fact =1;
		int value =1;
		Iterator<Integer> valueIterator;
		
		for (int i = 1; i <=n; i++) 
			fact *= i; 
		
		for (Character element : letters) 
			myMap.put(element, (myMap.getOrDefault(element, 0) +1));
		
		valueIterator = myMap.values().iterator();
		
		while (valueIterator.hasNext()) 
			value *= (int)valueIterator.next();
		
		System.out.println(fact/value); 
	}
}
