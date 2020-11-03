package codility;

import java.util.ArrayList;
import java.util.Collections; 
import java.util.List;

public class ArraysSortBasedIndex {

	public static void main(String[] args) {
		int[] A = {1,6,5,7,9};
		List<Integer> aList = new ArrayList<>();
		List<Integer> sortedList = new ArrayList<>();
		List<Integer> sortedindicesList = new ArrayList<>();
		
		for (int i = 0; i < A.length; i++) 
			aList.add(A[i]);
		
		sortedList.addAll(aList);
		Collections.sort(sortedList); 
		
		for (Integer integer : sortedList) 
			sortedindicesList.add(aList.indexOf(integer));
	}
}