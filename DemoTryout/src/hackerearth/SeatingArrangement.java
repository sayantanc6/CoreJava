package hackerearth;

public class SeatingArrangement {

	public static void main(String[] args) {
        int t = 2;
        int N = 47;
        int seatingPosition1 = 0;
        int seatingPosition2 =0;
        int rowNum =0;
        String seattype = ""; 
        int offset =0;
        int b=0; 
		rowNum = Math.abs(N/6); 
		float tmp = ((float)N/6)%1; 
        		
        		if (tmp > 0)  
					rowNum++;
        		
                if (rowNum%2 !=0) { 
                	System.out.println("rownum odd : "+rowNum);
                    seatingPosition1 = N%6;
					if (seatingPosition1 == 0 || seatingPosition1 == 1)
						seattype = "WS";
					else if (seatingPosition1 == 2 || seatingPosition1 == 5)
						seattype = "MS";
					else if (seatingPosition1 == 3 || seatingPosition1 == 4)
						seattype = "AS";
					offset = (int) Math.ceil(N / 12);
					switch (seatingPosition1) {
					case 0:
						b = 5;
						break;
					case 1:
						b = 0;
						break;
					case 2:
						b = 1;
						break;
					case 3:
						b = 2;
						break;
					case 4:
						b = 3;
						break;
					case 5:
						b = 4;
						break;
					}
					seatingPosition2 = ((offset * 12 + 12) - b);
					System.out.println(seatingPosition2+" "+seattype);
				}else {
                	System.out.println("rownum even : "+rowNum);
					seatingPosition1 = N%12;
					System.out.println("seatingPosition1 : "+seatingPosition1);
					if (seatingPosition1 == 0 || seatingPosition1 == 7)
						seattype = "WS";
					else if (seatingPosition1 == 8 || seatingPosition1 == 11)
						seattype = "MS";
					else if (seatingPosition1 == 9 || seatingPosition1 == 10)
						seattype = "AS";
					if (N%12 ==0) {
						offset = (int) Math.floor(N / 12); 
						offset -= 1;
						System.out.println("inside if offset : "+offset);
					}else {
						offset = (int) Math.floor(N / 12);
						System.out.println("inside else offset : "+offset);
					}
					switch (seatingPosition1) { 
					case 0:
						b = 1;
						break;
					case 7:
						b = 6;
						break;
					case 8:
						b = 5;
						break;
					case 9:
						b = 4;
						break;
					case 10:
						b = 3;
						break;
					case 11:
						b = 2;
						break;
					}
					seatingPosition2 = ((offset * 12) + b);
					System.out.println(seatingPosition2+" "+seattype);
				} 
	}
}
