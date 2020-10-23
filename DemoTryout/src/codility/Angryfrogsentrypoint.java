package codility;

public class Angryfrogsentrypoint {

	public static void main(String[] args) {
		AngryFrogs frogs = new AngryFrogs(); 
		int[] blocks = {1,1};     
		int result = frogs.solution(blocks);
		System.out.println(result); 
		System.out.println("index_max : "+frogs.index_max); 
	}

}
