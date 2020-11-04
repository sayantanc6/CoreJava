package codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Fish {

	/*
	 * Bad approach :- sgouldn't use 2 parallel Arrays. Instead, you should have a single array of objects.
	 * https://stackoverflow.com/questions/23339015/sorting-two-arrays-simultaneously
	 * link :- https://github.com/Mickey0521/Codility/blob/master/Fish.java
	 * */
	
	public static void main(String[] args) {
		int[] A = {4,3,2,1,5};
		int[] B = {0,1,0,0,0};  
		int[] sortedA = new int[B.length];
		int[] sortedB = new int[B.length];
		List<Integer> aList = new ArrayList<>();
		List<Integer> sortedList = new ArrayList<>();
		int current_extracted_index =0;
		int current_position=0;
		int countAlive =0;
		boolean alldirsame = false;
		String tmp = "";
		
		tmp = IntStream.of(B).boxed().map(String::valueOf).collect(Collectors.joining()); 
		
		if (tmp.indexOf('0') == -1) {
			alldirsame = true;
			countAlive = B.length;
		}
		
		if (tmp.indexOf('1') == -1) {
			alldirsame = true;
			countAlive = B.length;
		}
		
		if (!alldirsame) {
			for (int i = 0; i < B.length; i++)
				aList.add(A[i]);
			sortedList.addAll(aList);
			Collections.sort(sortedList);
			for (int i : sortedList) {
				current_extracted_index = sortedList.indexOf(i);
				current_position = aList.indexOf(i);
				sortedB[current_extracted_index] = B[current_position];
				sortedA[current_extracted_index] = A[current_position];
			}
			tmp = IntStream.of(sortedB).boxed().map(String::valueOf).collect(Collectors.joining()); 
			
			if (tmp.charAt(sortedB.length - 1) == '0')
				countAlive = tmp.lastIndexOf('0') - tmp.lastIndexOf('1');
			else if (tmp.charAt(sortedB.length - 1) == '1') 
				countAlive = tmp.lastIndexOf('1') - tmp.lastIndexOf('0');
			
		}
		System.out.println(countAlive);
	}
}