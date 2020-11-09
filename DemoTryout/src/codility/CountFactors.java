package codility;

public class CountFactors {

	public static void main(String[] args) {
		
		int N = 27043111;
		int count =2;
		
		if (N ==1) 
			count =1;
		
		if (N ==2) 
			count =2;
		
		if (N%2 ==0) 
			count++;
		
		if (N < 50) {
			for (int i = 3; i <= Math.ceil(N/2); i++) {
				if (N % i == 0)
					count++;
			} 
		}else {
			for (int i = 3; i <= Math.ceil(N/2); i++) {
				if (N % i == 0)
					count++; 
			}
		}
		System.out.println(count);
	}
}