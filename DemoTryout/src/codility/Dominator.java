package codility;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Dominator { 

	public static void main(String[] args) { 
		int[] A = {3,4,3,2,3,-1,3,3};
		Map<Integer, Integer> myMap = new ConcurrentHashMap<Integer,Integer>(); 
		int max =0;
		int dominator =0;
		
		if (A.length ==1)
			dominator =0;
		
		if (A.length == 0) 
			dominator =-1;
		
		
		if (dominator ==0) {
			for (Integer element : A)
				myMap.put(element, (myMap.getOrDefault(element, 0) + 1));
			max = Collections.max(myMap.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
			
			if (!(myMap.get(max) > (A.length/2))) 
				dominator =-1;
		}
		System.out.println(myMap); 
		
		if (dominator ==0) {
			for (int i = 0; i < A.length; i++) {
				if (A[i] == max) {
					dominator = i;
					break;
				}
			} 
		}
		System.out.println(dominator);
	}
}