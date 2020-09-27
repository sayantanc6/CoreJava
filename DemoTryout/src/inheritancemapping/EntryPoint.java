package inheritancemapping;

public class EntryPoint {
	
	/*source of information :-
	 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/ReflectionUtils.html
	 * https://stackoverflow.com/questions/3567372/access-to-private-inherited-fields-via-reflection-in-java
	 * */

	public static void main(String[] args) {
		Sub sub = new Sub("sayantan", "chatterjee", "sayantansub", "chatterjeesub");
		Super sup = new Super("sayantan", "chatterjee"); 
		Destination mapped = new Destination();
		System.out.println(sub);
		System.out.println(sup);
		ModelMapper2 mapper2= new ModelMapper2();
		mapped = (Destination)mapper2.withModelMapper(sub, mapped);
		System.out.println(mapped);
		
		ModelMapper mapper = new ModelMapper();
		try {
			mapped = (Destination)mapper.withoutModelMapper(sub, sup, mapped);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(mapped); 
	}
}
