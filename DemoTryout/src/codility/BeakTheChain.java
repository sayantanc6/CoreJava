package codility;

import java.util.ArrayList;
import java.util.Collections;

public class BeakTheChain {

	public static void main(String[] args) { 
		int[] A = {5,2,4,6,3,7};
		ArrayList<Integer> list = new ArrayList<Integer>(); 
		
		for (int i = 0; i < A.length; i++) { 
			for (int j = 0; j < A.length; j++) {
				if (i!=0 &&  j!=0 && i+1 != j && i!= j && i!= A.length-1 && j!=A.length-1 && i<j) {   
					list.add(A[i]+A[j]);
				}
			}
		}
		Collections.sort(list);
		System.out.println(list);
		System.out.println(list.get(0));  
	}
}
