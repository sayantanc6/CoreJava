package codility;

public class CyclicRotation {

	public static void main(String[] args) {
		
		int[] A = {3,8,9,7,6};
		int[] output = new int[A.length];
		int K =3;
		
		for (int i = 0; i < A.length; i++) {
			if (K <= A.length - 1) { 
				if (((A.length - 1) - i) < K) {
					output[((i + K) - (A.length - 1)) - 1] = A[i];
				} else {
					output[i + K] = A[i];
				} 
			}else {
				output[((i + K)%A.length)] = A[i];
			}
		}
		
		for (int i : output) {
			System.out.print(i+" "); 
		}
	}
}
