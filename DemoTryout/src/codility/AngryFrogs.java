package codility;


public class AngryFrogs {
	
	int index_max =0;

	public int solution(int[] blocks) {
 
        int max = blocks[this.index_max];  
		for (int i = this.index_max; i < blocks.length; i++) { 
			if (blocks[i] > max) {
				max = blocks[i];
				this.index_max = i;
			}
		}
		if (blocks.length-1 > this.index_max) { 
			 this.index_max+=1;
		}
		return this.index_max;  
	}
}