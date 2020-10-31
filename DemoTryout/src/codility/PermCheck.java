package codility;

import java.util.Arrays;

public class PermCheck {

	public static void main(String[] args) {
		
		int[] A = {4, 1,3,2};    
		int[] a1 = Arrays.stream(A).sorted().toArray();
		int initial = 1;
		int result =1;
		
		for (int i = 0; i < a1.length; i++) {
			if (initial == a1[i]) {
				initial++;
			}else{
				result =0;
				break;
			}
		}
		System.out.println(result);
	}
}
