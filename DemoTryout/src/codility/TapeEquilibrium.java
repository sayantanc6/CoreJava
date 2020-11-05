package codility;

public class TapeEquilibrium {

	public static void main(String[] args) {
		int[] A= {3,1,2,4,3};
		int totalSum =0;
		int operand1 =0;
		int operand2 =0;
		int mindiff =Integer.MAX_VALUE;
		int diff=0;
		
		for (int i = 0; i < A.length; i++)
			totalSum += A[i];
		
		for (int p = 1; p < A.length; p++) {
			 operand1 += A[p - 1];
			 operand2 = totalSum - operand1;
			 diff = operand1 - operand2;
			 if (diff < 0) 
				diff = -diff; 
			 
			 mindiff = Math.min(diff, mindiff);
		}
		System.out.println(mindiff); 
	}
}
