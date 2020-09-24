package functionalinterface;

import java.util.Arrays;
import java.util.List;

public class ExceptionHandlingEntryPoint {
	
	/*
	 * source of information :- 
	 * https://www.baeldung.com/java-lambda-exceptions
	 * https://dzone.com/articles/how-to-handle-checked-exception-in-lambda-expressi
	 * */

	public static void main(String[] args) {
		List<Integer> integers = Arrays.asList(3,0, 9, 7, 6, 10, 20);
		
/*		integers.forEach(i -> {
		    try {
		        System.out.println(50 / i);
		    } catch (ArithmeticException e) {
		        System.err.println(
		          "Arithmetic Exception occured : " + e.getMessage());
		    }
		}); 
*/
		integers.forEach(ThrowingConsumer.consumerWrapper(i -> System.out.println(50 / i), ArithmeticException.class));	
		}
	
	
}
