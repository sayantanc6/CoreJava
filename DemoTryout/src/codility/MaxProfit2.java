package codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MaxProfit2 {

	public static void main(String[] args) {
		int[] A = {23171,21011,21123,21366,21013,21367}; 
		List<MyObject> myList = new ArrayList<>();  
		int maxDiff=0; 
		int tmp_value = 0;
		int tmp_index =0;

		for (int i = 0; i < A.length; i++) 
			myList.add(new MyObject(A[i], i));   
		 
		Collections.sort(myList, Collections.reverseOrder());
		
		for (int i = 0; i < A.length; i++) 
			System.out.println(myList.get(i)); 
		
		tmp_index = myList.get(0).getPos();
		tmp_value = myList.get(0).getSize();
		System.out.println(tmp_index);
		
		for (int i = 0; i < A.length; i++) {
			int curr_val =  myList.get(i).getSize();
			int curr_index = myList.get(i).getPos();
			if (tmp_index > curr_index) {
				int curr_diff = myList.get(tmp_index).getSize() - myList.get(curr_index).getSize();
				System.out.println(curr_diff);
				 if (maxDiff < curr_diff) {
					maxDiff = curr_diff;
				}
			}
		}
		System.out.println(maxDiff);
	}
	
	public static class MyObject implements Comparable<MyObject>{
		
		int size;
		int pos;
		
		public MyObject(int size, int pos) {
			super();
			this.size = size;
			this.pos = pos;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		@Override
		public int compareTo(MyObject o) {
			return this.getSize() - o.getSize();
		}

		@Override
		public String toString() {
			return "MyObject [size=" + size + ", pos=" + pos + "]";
		}
	}
}
