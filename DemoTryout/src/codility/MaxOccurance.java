package codility;

import java.util.Arrays;

public class MaxOccurance {

	public static void main(String[] args) {
		int[] a = {4,3,4,4,4,2};
		int previous = a[0];
	    int leader = a[0];
	    int count = 1;
	    int maxCount = 1;

		  if (a == null || a.length == 0)
		        leader = -1;
		  
		  if (a.length ==1) 
			leader = a[0];

		    Arrays.sort(a);

		    for (int i = 1; i < a.length; i++) {
		        if (a[i] == previous)
		            count++;
		        else {
		            if (count > maxCount) {
		            	leader = a[i-1]; 
		                maxCount = count;
		            }
		            previous = a[i];
		            count = 1; 
		        } 
		    }
		    leader = count > maxCount ? a[a.length-1] : leader;
		    if (!(count > (a.length/2))) 
				leader = -1;
		    
		    System.out.println("leader : "+leader); 
		    System.out.println("count : "+count); 

	}
}