package hackerearth;

public class OutputFormatting {

    public static void main(String[] args) {
    	for (int y=1; y<12; y++)
        {
    		String s1="abc";
            System.out.printf("%-10s %03d",s1,y); 
    		System.out.println();
        }
    }
}
