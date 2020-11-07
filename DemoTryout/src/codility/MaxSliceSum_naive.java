package codility;

import java.util.Arrays;

public class MaxSliceSum_naive { 
 
	public static void main(String[] args) {
		int[] A = {3,2,-6,4,0};
		 int maxSum = Integer.MIN_VALUE; 
		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) { 
				if (i == j) 
					maxSum = Math.max(maxSum, A[i]);
				else 
					maxSum = Math.max(maxSum, Arrays.stream(Arrays.copyOfRange(A, i, (j + 1))).sum());
			}
		}
		 System.out.println(maxSum); 
	}
}