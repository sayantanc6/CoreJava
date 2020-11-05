package codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArraysSortBasedMultiple {

	public static void main(String[] args) {
		char[] letters = {'b','a','d','c'};
		String[] words = {"ball","apple","dog","cat"}; 
		int[] alphabeticalorder = {2,1,4,3};
		
		List<Myobject> oMyobjects = new ArrayList<>();
		
		for (int i = 0; i < alphabeticalorder.length; i++) 
			oMyobjects.add(new Myobject(letters[i], words[i], alphabeticalorder[i]));
		
		System.out.println("before : ");
		for (int i = 0; i < alphabeticalorder.length; i++) 
			System.out.println(oMyobjects.get(i));
		
		Collections.sort(oMyobjects);
		
		System.out.println("after : ");
		for (int i = 0; i < alphabeticalorder.length; i++) 
			System.out.println(oMyobjects.get(i)); 
	}
	
	public static class Myobject implements Comparable<Myobject> {
		char letter;
		String word;
		int alphabetical;
		
		public Myobject(char letter, String word, int alphabetical) {
			super();
			this.letter = letter;
			this.word = word;
			this.alphabetical = alphabetical;
		}

		public char getLetter() {
			return letter;
		}

		public void setLetter(char letter) {
			this.letter = letter;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public int getAlphabetical() {
			return alphabetical;
		}

		public void setAlphabetical(int alphabetical) {
			this.alphabetical = alphabetical;
		}

		@Override
		public int compareTo(Myobject o) {
			return this.letter - o.letter;
		}

		@Override
		public String toString() {
			return "Myobject [letter=" + letter + ", word=" + word + ", alphabetical=" + alphabetical + "]";
		}
	}
}
