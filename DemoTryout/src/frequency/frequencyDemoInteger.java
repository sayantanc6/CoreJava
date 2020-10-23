package frequency;

import java.util.HashMap;
import java.util.Map;

public class frequencyDemoInteger {

	public static void main(String[] args) {
		
		int[] s1 = {9,3,9,3,9,7};
		int result = 0;
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		for (int element : s1) {  
			if (myMap.containsKey(element)) {
				myMap.replace(element, myMap.get(element), myMap.get(element) + 1); 
			} else {
				myMap.put(element, 1);  
			}
		}
		System.out.println(myMap); 
		for (Map.Entry<Integer, Integer> entry : myMap.entrySet()) {
			 if (entry.getValue() == 1) {
				 result = entry.getKey();
			}
		}
		System.out.println(result); 
	}

}
