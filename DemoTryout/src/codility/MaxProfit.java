package codility;

public class MaxProfit {

	public static void main(String[] args) {
		int[] A = {8, 9, 3, 6, 1, 2}; 
		int maxDiff =0;
		int result =0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				if (A[j] > A[i]) {
					if (maxDiff < (A[j] - A[i])) {
						maxDiff = (A[j] - A[i]);
					}
				}
			}
		}
		System.out.println(maxDiff);
	}
}
