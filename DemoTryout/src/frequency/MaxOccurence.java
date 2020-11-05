package frequency;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MaxOccurence { 

	public static void main(String[] args) { 
		int[] A = {3,4,3,2,3,-1,3,3};
		int max =0;
		
		Map<Integer, Integer> myMap = new HashMap<Integer, Integer>();
		for (Integer element : A) 
				myMap.put(element, (myMap.getOrDefault(element, 0) +1));
		
		System.out.println(myMap);
		max = Collections.max(myMap.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		System.out.println(max);
	}
}
