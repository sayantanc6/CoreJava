package codility;
import java.util.Arrays;
 
public class MissingInteger {

	public static void main(String[] args) {
		int[] a = {1, 3, 6, 4, 1, 2};   
		int min =1;
		int[] a1 = Arrays.stream(a).sorted().distinct().toArray();
 
		for (int i = 0; i < a1.length; i++) {
			if (a1[i] < 1) {
				continue;
			}else if (a1[i] == min) { 
				min++;
			}else {
				break;
			}
	    } 
		System.out.println(min);   
	}
}
