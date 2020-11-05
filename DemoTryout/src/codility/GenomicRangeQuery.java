package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
public class GenomicRangeQuery {

	public static void main(String[] args) {
		String S= "CAGCCTA";
		int[] P = {2,5,0};
		int[] Q = {4,5,6};
		char[] neucleotides = S.toCharArray();
		int[] tmp = new int[neucleotides.length];
		int[] impact_factor = new int[neucleotides.length];
		int[] result1;
		List<Integer> result = new ArrayList<>();
		Map<Character, Integer> myMap = new HashMap<>();
		
		myMap.put('A', 1);
		myMap.put('C', 2);
		myMap.put('T', 4);
		myMap.put('G', 3);
		
		for (int i = 0; i < impact_factor.length; i++) 
			impact_factor[i] = myMap.get(neucleotides[i]);
		
		 
		for (int i = 0; i < P.length; i++) {
			tmp = Arrays.copyOfRange(impact_factor, P[i], (Q[i] + 1));
			Arrays.sort(tmp);  
			result.add(tmp[0]);
		}
		
		result1 = new int[result.size()];
		for (int i = 0; i < result.size(); i++) 
			result1[i] = result.get(i).intValue();
		
		System.out.println(Arrays.toString(result1));
 	} 
}