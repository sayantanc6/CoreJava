package functionalinterface;

import java.util.function.Function;

public class CurryingEntryPoint {
	/*
	 * source of information :-
	 * https://www.geeksforgeeks.org/currying-functions-in-java-with-examples/
	 * https://www.baeldung.com/java-currying
	 * https://dzone.com/articles/whats-wrong-java-8-currying-vs
	 */

	public static void main(String[] args) {
		Function<String, Function<Address, Person>> createPerson = name -> address -> new Person(name, address);
		System.out.println(createPerson.apply("sayantan").apply(new Address("india"))); 
	}
}
