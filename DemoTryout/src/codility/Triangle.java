package codility;

import java.util.Arrays;

public class Triangle {

	public static void main(String[] args) {
		int[] A = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};   
		long[] A1 = new long[A.length];
 		boolean triangular = false;
		int result = 0;
		
		for (int i = 0; i < A1.length; i++) 
			A1[i] = Long.valueOf(A[i]);
		
		Arrays.sort(A1);
		
		for (int p = 0,q =1,r=2 ; p <= (A1.length -3) && q <= (A1.length -2) && r <= (A1.length -1); p++,q++,r++) {
			if (((A1[p] + A1[q]) > A1[r]) && ((A1[q] + A1[r]) > A1[p]) && ((A1[r] + A1[p]) > A1[q])) 
				triangular = true;
		}
		
		if (triangular) 
			result = 1;
		else 
			result =0;
		
		System.out.println(result); 
	}
}
