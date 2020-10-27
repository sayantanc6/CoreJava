package codility;

import java.util.HashSet;
import java.util.Set;

public class NailingPlanks {

	public static void main(String[] args) {
		int[] A = {1,4,5,8};
		int[] B = {4,5,9,10};
		int[] C = {4,6,7,10,2};
		
		int result =0;
		Set<Integer> plankNailed = new HashSet<Integer>();
		
		for (int i=0; i < C.length ; i++) {
			for (int k=0; k < A.length ; k++) {
				if (A[k] <= C[i] && C[i] <= B[k]) {
					plankNailed.add(A[k]+B[k]);   
				}
			}
		}
		if (plankNailed.size() == A.length) {
			result = plankNailed.size();
		}else if (plankNailed.size() < A.length) {
			result = -1;
		}
		System.out.println(result);
	}
}
