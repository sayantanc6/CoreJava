package codility;

public class PassingCars {

	public static void main(String[] args) {
		
		int[] A = {0,1,0,1,1};
		int pairs =0;
		
		for (int p = 0; p < A.length; p++) {
			for (int q = 0; q < A.length; q++) { 
				if (p >= 0 && p < q && q < A.length && p!=q && A[p]==0 && A[q]==1) 
					pairs++;
			}
		}
		System.out.println(pairs);
	}

}
