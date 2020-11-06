package codility;

import java.util.Arrays;

public class EquiLeader {
	
	public static void main(String[] args) {
		int[] A = {4,8};
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
	
	public static int findLeader(int[] a) {

		int[] a1 = new int[a.length];
		a1 = Arrays.copyOf(a, a.length);
		int previous = 0;
	    int leader = 0;
	    int count = 1;
	    int maxCount = 1;

		  if (a1 == null || a1.length == 0)
		        return -1;
		  
		  if (a1.length ==1) 
			return a1[0];
		  
		    Arrays.sort(a1);

		    for (int i = 1; i < a.length; i++) {
		        if (a1[i] == previous)
		            count++; 
		        else {
		            if (count > maxCount) {
		            	leader = a1[i-1]; 
		                maxCount = count;
		            }
		            previous = a1[i];
		            count = 1; 
		        } 
		    }
		    leader = count > maxCount ? a1[a1.length-1] : leader;
		    if (!(count > (a1.length/2)))  
				return -1;
		    
		    return leader; 
	}
}
