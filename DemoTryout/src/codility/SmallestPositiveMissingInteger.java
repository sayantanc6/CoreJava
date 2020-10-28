package codility;

import java.util.Arrays;

 
public class SmallestPositiveMissingInteger {

	public static void main(String[] args) {
		int[] a = {1, 3, 6, 4, 1, 2}; 
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
