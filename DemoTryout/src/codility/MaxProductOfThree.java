package codility; 

import java.util.Arrays;
import java.util.Comparator;

public class MaxProductOfThree {
 
	public static void main(String[] args) {
		int[] A = {-5, -6, -4, -7, -10};    
		int result =0;
		int[] positives = Arrays.stream(A).filter(x -> x >= 0).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray(); 
		int plength = positives.length;
		
		int[] negatives = Arrays.stream(A).filter(x -> x <= 0).boxed().sorted().mapToInt(Integer::intValue).toArray();
		int nlength = negatives.length; 
		
		System.out.println(Arrays.toString(positives));
		System.out.println(Arrays.toString(negatives)); 
		
		if (plength ==0 && nlength ==0) {
			result =0;
		}else if(plength >= 3 && (nlength ==0 || nlength < 2)) {   
			result = (positives[0] * positives[1] * positives[2]);
		}else if (plength == 0 && nlength >= 3) {
			result = (negatives[(nlength -1)] * negatives[(nlength -2)] * negatives[nlength - 3]);  
		}else if (nlength >=2  && plength >= 3){  
				int result1 = (negatives[0] * negatives[1] * positives[0]);  
				int result2 = (positives[0] * positives[1] * positives[2]);
				result = Math.max(result1, result2);
		}else if (nlength >=2  && plength <= 2) {
			 result = (negatives[0] * negatives[1] * positives[0]); 
		}
		
		System.out.println(result);
	}
}