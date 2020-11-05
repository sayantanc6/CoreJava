package codility;

import java.util.Stack;

public class Brackets {
 
	public static void main(String[] args) {
		String S = "{{{{))))";    
		char[] brackets = new char[S.length()];
		int result = 1; 
		int num_OpeningBracket =0, num_ClosingBracket =0;
		char poppedOpeningBracket;
		Stack<Character> myStack = new Stack<Character>();
		
		 if (S.isEmpty()) 
			 result =1;
		
		 brackets = S.toCharArray(); 
		 for (Character c : brackets) {
			if (c == '(' || c == '{' || c == '[') 
				num_OpeningBracket++;
			else if (c == ')' || c == '}' || c == ']') 
				num_ClosingBracket++;
		}
		 if (num_ClosingBracket == num_OpeningBracket) 
			result =1;
		 else 
			result =0;
		 
		 for (Character c : brackets) {
			if (c == '(' || c == '{' || c == '[') {
				myStack.push(c);
			}else if (c == ')' || c == '}' || c == ']') {
					if (!myStack.isEmpty()) {
						poppedOpeningBracket = myStack.pop();
						if ((poppedOpeningBracket == '(' && c == ')') || (poppedOpeningBracket == '{' && c == '}')
								|| (poppedOpeningBracket == '[' && c == ']'))

							continue;
						else {
							result = 0;
							break;
						} 
					}else {
						result = 0;
						break;
					}
			}else {
				result =0;
				break;
			}
		}
		 System.out.println(result);
	}
}
