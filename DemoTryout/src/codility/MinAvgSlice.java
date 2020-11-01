package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MinAvgSlice {

	public static void main(String[] args) {
		int[] A = {4,2,2,5,1,5,8};
		int[] tmp = new int[A.length];
		int minimum_avg_slice = Integer.MAX_VALUE;
		List<Integer> avg_slices = new ArrayList<>(); 
		List<Integer> starting_positions = new ArrayList<>(); 
		int tmp_sum=0; 
		double avg_slice =0;
		int minimal_avg_slice =0;
		
		for (int p = 0; p < A.length; p++) {
			for (int q = 1; q < A.length; q++) {
				if (q > p) {
					tmp = Arrays.copyOfRange(A, p, (q + 1));
					tmp_sum = Arrays.stream(tmp).sum();
					avg_slice = ((double)tmp_sum / tmp.length); 
					minimal_avg_slice = Math.min((int)Math.ceil(avg_slice), minimum_avg_slice); 
					avg_slices.add(minimal_avg_slice);  
					starting_positions.add(p);
				}
			} 
		}
		
		System.out.println(starting_positions.get(avg_slices.indexOf(Collections.min(avg_slices))));  
	}
}
