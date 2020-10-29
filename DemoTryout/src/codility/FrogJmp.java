package codility;

public class FrogJmp {

	public static void main(String[] args) {
		
		int X = 10;
		int Y = 85;
		int D = 30;
		int n =1;
		 
		if (Y >= X) {
			n = (int)Math.ceil(((double)(Y - X))/D);
			System.out.println(n);   
		} else {
			n =0;
			System.out.println(n); 
		}
	}
}
