package frequency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MaxOccurence2 {

	public static void main(String[] args) {
		int[] A = {3,4,3,2,3,-1,3,3};
		List<Integer> aList = new ArrayList<Integer>();
		int max =0;
		int maxtemp=0;
        int leader = 0; 

		for (Integer integer : A) {
			aList.add(integer); 
		} 
		
		System.out.println(Arrays.toString(aList.toArray())); 
		
		for (int i = 0; i < A.length; i++) { 
			max = Math.max(Collections.frequency(aList, A[i]), max);
			leader = maxtemp != max ? A[i] : leader;
            max = maxtemp;
		}
		System.out.println(leader); 
	}
}
