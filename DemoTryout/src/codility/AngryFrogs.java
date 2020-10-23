package codility;

import java.util.Scanner;

public class AngryFrogs {
	
	int initial_position_index=0;
	int result =0;
	int max;
	
	public int solution(int[] blocks) {		
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("what is your initial position : ");
		initial_position_index = scanner.nextInt();
		
		max = blocks[initial_position_index];
		if(initial_position_index == 0 && initial_position_index < (blocks.length-1)) {
			for (int i = 0; i < blocks.length; i++) {
				if (blocks[i] > max) {
					 max = blocks[i];
					 result = i+1;
				}
			}
		}else if(initial_position_index > 0 && initial_position_index < (blocks.length -1)) { 
			for (int i = initial_position_index; i < blocks.length; i++) {
				if (blocks[i] > max) {
					 max = blocks[i];
					 result = i;
				}
			}
//			max = blocks[initial_position_index];
//			for (int j = initial_position_index; j >=0; j--) { 
//				if (blocks[j] > max) {
//					 max = blocks[j];
//					 result += j;
//				}
//			}
		}else {
			for (int i = (blocks.length-1); i >= 0; i--) {
				if (blocks[i] > max) {
					max = blocks[i];
					 result = i;
				}
			}
		}
		return result;
	}
}