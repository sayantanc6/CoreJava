package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArraysSortBasedMultiple {

	public static void main(String[] args) {
		char[] letters = {'b','a','d','c'};
		String[] words = {"ball","apple","dog","cat"}; 
		int[] alphabeticalorder = {2,1,4,3};
		char[] sortedletters = new char[letters.length];
		int[] sortedalphabeticalorder = new int[letters.length];
		String[] sortedwords = new String[letters.length];
		List<Character> lettersList = new ArrayList<>();
		List<Character> sortedlettersList = new ArrayList<>();
		int current_index =0;
		int sorted_extracted_index =0;
		
		for (int i = 0; i < letters.length; i++) 
			lettersList.add(letters[i]);
		
		sortedlettersList.addAll(lettersList);
		Collections.sort(sortedlettersList); 
		
		for (Character character : sortedlettersList) { 
			sorted_extracted_index = lettersList.indexOf(character);
			current_index = sortedlettersList.indexOf(character);
			sortedletters[sorted_extracted_index] = letters[current_index];
			sortedwords[sorted_extracted_index] = words[current_index]; 
			sortedalphabeticalorder[sorted_extracted_index] = alphabeticalorder[current_index];    
		}
		
		System.out.println("letters : "+Arrays.toString(letters)); 
		System.out.println("words : "+Arrays.toString(words)); 
		System.out.println("alphabetical order : "+Arrays.toString(alphabeticalorder));
		System.out.println("sorted letters : "+Arrays.toString(sortedletters)); 
		System.out.println("sorted words : "+Arrays.toString(sortedwords));
		System.out.println("sorted alphabetical order : "+Arrays.toString(sortedalphabeticalorder));
	}

}
