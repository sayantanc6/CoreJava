package functionalinterface;

import java.util.function.Function;

public class FuncCompositionEntryPoint {

	public static void main(String[] args) {
		Person person = new Person("sayantan",new Address("india"));
		 
		Function<Person, Address> personToAddress = Person::getAddress;
		Function<Address, String> adreesTocountry = Address::getCountry;
		
		Function<Person, String> personToCountry = personToAddress.andThen(adreesTocountry);
		System.out.println(personToCountry.apply(person));  
	}

}
