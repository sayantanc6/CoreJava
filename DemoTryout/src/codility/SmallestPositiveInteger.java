package codility;

import java.util.Arrays;


public class SmallestPositiveInteger {

	public static void main(String[] args) {
		int[] a = {-3,-2,0,1,1,5,3,4};
		int min =0;
		a = Arrays.stream(a).filter(x -> x >=0).sorted().distinct().toArray();
		
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
