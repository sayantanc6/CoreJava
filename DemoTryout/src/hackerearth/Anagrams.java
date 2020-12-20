package hackerearth;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagrams { 

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Map<Character, Integer> mymap1 = new HashMap<>();
		Map<Character, Integer> mymap2 = new HashMap<>();
        int t = scanner.nextInt();
        scanner.nextLine();
        String s1 = "";
        String s1copy = "";
        String s2 = "";
        char currentkey = '\0'; 
        int count =0;
        int diff =0;

        if (t > 0) {
        	s1 = scanner.nextLine();
			s1copy = s1;
			
			while (t > 0) {
				for (int i = 0; i < s1.length(); i++) 
					mymap1.put(s1.charAt(i), (mymap1.getOrDefault(s1.charAt(i), 0) + 1));
				
				System.out.println(mymap1);
				s2 = scanner.nextLine();
				
				for (int i = 0; i < s2.length(); i++)  
					mymap2.put(s2.charAt(i), (mymap2.getOrDefault(s2.charAt(i), 0) + 1));
				
				System.out.println(mymap2);
				
				for (int i = 0; i < s1.length(); i++) {
					currentkey = s1.charAt(i);
					if(mymap1.containsKey(currentkey) && mymap2.containsKey(currentkey)) {
					   diff = Math.abs(mymap1.get(currentkey) - mymap2.get(currentkey));
					   count += diff;
					   mymap1.remove(currentkey);
					   mymap2.remove(currentkey);
					}else if (mymap1.containsKey(currentkey) && !mymap2.containsKey(currentkey)) {
						count += mymap1.get(currentkey);
						mymap1.remove(currentkey);
					}else if (!mymap1.containsKey(currentkey) && mymap2.containsKey(currentkey)) {
						count += mymap2.get(currentkey); 
						mymap2.remove(currentkey);
					}
				}
				
				for (int i = 0; i < s2.length(); i++) {
					currentkey = s2.charAt(i);
					if(mymap1.containsKey(currentkey) && mymap2.containsKey(currentkey)) {
					   diff = Math.abs(mymap1.get(currentkey) - mymap2.get(currentkey));
					   count += diff;
					   mymap1.remove(currentkey);
					   mymap2.remove(currentkey);
					}else if (mymap1.containsKey(currentkey) && !mymap2.containsKey(currentkey)) {
						count += mymap1.get(currentkey);
						mymap1.remove(currentkey);
					}else if (!mymap1.containsKey(currentkey) && mymap2.containsKey(currentkey)) {
						count += mymap2.get(currentkey); 
						mymap2.remove(currentkey);
					} 
				}
				
				mymap1.clear();
				mymap2.clear();
				currentkey = '\0';
				s1 = s1copy;
				System.out.println("count : "+count);
		        count =0; 
				t--;
			}
		}else 
			System.exit(0);
	}
}
