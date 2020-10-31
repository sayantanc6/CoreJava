package codility;

public class CountDiv2 {

	public static void main(String[] args) {
		int A = 6;
		int B = 11;
		int K = 2;
		int count =0;
		
		if (A%K == 0) {
			count = B/K - A/K + 1; 
		}else {
			count = B/K - A/K;
		}
		
		System.out.println(count);
	}

}
