package codility;

public class LonelyInteger {

	public static void main(String[] args){
		int i=42;
		int a = 0b00000000000000000000000000101010; // binary format assign
		 a <<= 5;
		    System.out.println("left shift : "+a);
		System.out.println("binary format : "+Integer.toBinaryString(i));
		System.out.println("integer format : "+a);
	    System.out.println("unary operator : "+Integer.toBinaryString(~a)); 
	   
	}


}
