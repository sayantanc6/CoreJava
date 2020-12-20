package dto.inheritancemapping;

public class EntryPoint {
	
	/*source of information :-
	 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/ReflectionUtils.html
	 * https://stackoverflow.com/questions/3567372/access-to-private-inherited-fields-via-reflection-in-java
	 * https://commons.apache.org/proper/commons-lang/apidocs/index.html?org/apache/commons/lang3/reflect/FieldUtils.html
	 * https://stackoverflow.com/questions/3567372/access-to-private-inherited-fields-via-reflection-in-java
	 * */

	public static void main(String[] args) throws IllegalAccessException,IllegalArgumentException{
//		Sub sub = new Sub("sayantan", "chatterjee", "sayantansub", "chatterjeesub");
//		Super sup = new Super("sayantan", "chatterjee"); 
//		System.out.println(sub);
//		System.out.println(sup);
//		Destination dest = new Destination();
//		ModelMapper mapper = new ModelMapper();
//		dest = (Destination)mapper.inheritanceToObject(sub, dest);
//		System.out.println(dest); 
		
		Destination source = new Destination();
		Sub sub = new Sub();
		Super sup = new Super();
		source.setFirstname("sayantan");
		source.setLastname("chatterjee");
		source.setFirstSubname("sayantansub");
		source.setLastSubname("chatterjeesub"); 
		
		InheritanceMapper mapper = new InheritanceMapper();
		mapper.objectToInheritence(source,sub,sup);
		System.out.println(source);  
		System.out.println(sub); 
		System.out.println(sup); 
	}
}
