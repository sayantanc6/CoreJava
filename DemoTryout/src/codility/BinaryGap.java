package codility;

import java.util.Arrays;

public class BinaryGap {

	public static void main(String[] args) {
		int N = 6;
		int numZeroes =0;
		int numOnes =0;
		int gap =0;
		
		char[] arr1 = Integer.toBinaryString(N).toCharArray();
		

		System.out.println(Arrays.toString(arr1)); 
		
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] == '1') {
				numOnes++;
				if (numZeroes > gap) {
					gap = numZeroes;
				}
				if (numOnes > 1) {
					numZeroes =0;
				}
			}else if (arr1[i] == '0' && numOnes > 0) { 
				numZeroes++;
				if (i == (arr1.length -1) && numOnes == 1) {
					gap =0;
				}
				if (i == (arr1.length -1) && numOnes > 1 && numZeroes >= 1) {
					continue;
				}
			}
		}
		System.out.println("difference : "+gap); 	           
	}
}
