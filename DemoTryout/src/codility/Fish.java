package codility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Fish {

	public static void main(String[] args) {
		int[] A = {4,8};
		int[] B = {0,0}; 
		List<MyObject> myojectsList = new ArrayList<MyObject>();
		int countAlive =0;
		boolean alldirsame = false;
		String tmp = "";
		ListIterator<MyObject> li;

		
		for (int i = 0; i < B.length; i++) 
			myojectsList.add(new MyObject(A[i], B[i]));
				
		tmp = IntStream.of(B).boxed().map(String::valueOf).collect(Collectors.joining()); 
		
		if (tmp.indexOf('0') == -1) {
			alldirsame = true;
			countAlive = B.length;
		}
		
		if (tmp.indexOf('1') == -1) {
			alldirsame = true;
			countAlive = B.length;
		}
		
		if (A.length == 1) {
			alldirsame = true;
			countAlive = 1;
		}
		
		if (A.length == 2 && (A[0] != A[1]) && (B[0] != B[1])) {
			alldirsame= true;
			countAlive =1;
		}else if (A.length == 2 && (A[0] != A[1]) && (B[0] == B[1])) {
			alldirsame= true;
			countAlive =2;
		}
		
		if (!alldirsame) {
			 Collections.sort(myojectsList);
			 
			 if (myojectsList.get(A.length -1).getDir() ==0) {
				 li= myojectsList.listIterator(myojectsList.size());
				 while (li.hasPrevious()) {
					MyObject myObject = (MyObject) li.previous();
					if (myObject.getDir() ==0) 
						countAlive++;
					else 
						break;
				}
			}
			 
			 if (myojectsList.get(A.length -1).getDir() ==1) {
				 li= myojectsList.listIterator(myojectsList.size());
				 while (li.hasPrevious()) {
					MyObject myObject = (MyObject) li.previous();
					if (myObject.getDir() ==1) 
						countAlive++;
					else 
						break;
				} 
			}
		}
		
		System.out.println(countAlive);
	}

	public static class MyObject implements Comparable<MyObject>{
		int size;
		int dir;
		
		public MyObject(int size, int dir) {
			super();
			this.size = size;
			this.dir = dir;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getDir() {
			return dir;
		}

		public void setDir(int dir) {
			this.dir = dir;
		}

		@Override
		public int compareTo(MyObject o) {
			return this.getSize() - o.getSize();
		}

		@Override
		public String toString() {
			return "MyObject [size=" + size + ", dir=" + dir + "]";
		}
	}
}
