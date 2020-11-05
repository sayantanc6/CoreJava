package codility;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SortedMapByValues_FindLargestOccurence {

	public static void main(String[] args) {
		int[] A = {3,4,3,2,3,-1,3,3};
		int max =0;
		Map<Integer, Integer> myMap = new ConcurrentHashMap<Integer,Integer>(); 
		Map<Integer,Integer> sortedMap = new HashMap<>();
		
		for (Integer element : A)
			myMap.put(element, (myMap.getOrDefault(element, 0) + 1));
		
		max = Collections.max(myMap.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		
		System.out.println(myMap); 
		
		sortedMap = myMap.entrySet().stream().sorted(Entry.comparingByValue((a,b)-> b-a)) 
		    	.collect(Collectors.toMap(Entry::getKey, Entry::getValue,(e1, e2) -> e1, LinkedHashMap::new));
		
		System.out.println(sortedMap); 
	}
}