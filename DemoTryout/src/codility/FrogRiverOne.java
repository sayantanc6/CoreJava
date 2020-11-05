package codility;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FrogRiverOne {

	public static void main(String[] args) {
		int X =3;
		int[] A = {1,3,1,3,2,1,3};  
		boolean ismapFull;
		Map<Integer, Integer> myMap = new HashMap<>(); 
		int result =0;
		
		for (int i = 0; i < A.length; i++) {
			myMap.put(A[i], i);
			Set<Integer> mySet = myMap.keySet();
			if (mySet.size() == X) {
				ismapFull = true;
				result = i;
				  break;
			}

		} 
		
		for (int j = 1; j <= X; j++) { 
			if (!myMap.containsKey(j)) {
				result = -1;
				break;
			}
		}
		 
		System.out.println(result);
	}
}
