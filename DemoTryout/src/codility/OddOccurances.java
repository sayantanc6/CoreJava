package codility;

import java.util.HashMap;
import java.util.Map;

public class OddOccurances {

	public static void main(String[] args) {
		
		int[] s1 = {9,3,9,3,9,7,9};
		int result = 0;
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		for (int element : s1) {  
			if (myMap.containsKey(element)) {
				myMap.replace(element, myMap.get(element), myMap.get(element) + 1); 
			} else {
				myMap.put(element, 1);  
			}
		}
		for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
			 if (entry.getValue()%2 != 0) {
				 result = entry.getKey();
			}
		}
		System.out.println(result); 
	}

}