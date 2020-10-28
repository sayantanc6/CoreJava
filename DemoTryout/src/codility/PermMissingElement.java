package codility;

import java.util.Arrays;

public class PermMissingElement { 

	public static void main(String[] args) {
		int[] a = {2, 3, 1, 5};  
		int min =1;
		a = Arrays.stream(a).filter(x -> x > 0).sorted().distinct().toArray();

		for (int i : a) {
			if (min == i) {
				min++;
			}else {
				break; 
			}
		}
		System.out.println(min);
	}

}
