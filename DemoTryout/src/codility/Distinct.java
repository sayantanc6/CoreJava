package codility;

import java.util.Arrays;

public class Distinct {

	public static void main(String[] args) {
		int[] A = {2,1,1,2,3,1};
		System.out.println(Math.toIntExact(Arrays.stream(A).distinct().count())); 
		
	}

}
