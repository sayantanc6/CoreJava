package codility;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EquiLeader {
	
	public static void main(String[] args) {
		int[] A = {4,3,4,4,4,2};
		int[] sequence_1 = new int[A.length];
		int[] sequence_2 = new int[A.length];
		int leader =0;
		int equileader=0;
		
		leader = findLeader(A);
		
		for (int i = 0; i < A.length; i++) {
			sequence_1 = Arrays.copyOfRange(A, 0, (i + 1));
			sequence_2 = Arrays.copyOfRange(A, (i + 1), A.length);
			
			if (leader == -1) 
				break;
			
			if ((findLeader(sequence_1) == leader && findLeader(sequence_2) == leader)) 
				equileader++;
			
		}
		System.out.println(equileader);
	}
	
	public static int findLeader(int[] A) {
		Map<Integer, Integer> myMap = new ConcurrentHashMap<Integer,Integer>();  
		int leader =0;
		
		if (A.length ==1)
			return A[0];
		
		if (A.length == 0) 
			return -1;
		
		
		if (leader ==0) {
			for (Integer element : A) 
				myMap.put(element, (myMap.getOrDefault(element, 0) + 1));
			leader = Collections.max(myMap.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
			
			if (!(myMap.get(leader) > (A.length/2))) 
				leader =-1;
		}
		return leader;
	}
}
