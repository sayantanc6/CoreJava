package streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsEntryPoint {
	
	/*
	 * source of information :-
	 * http://tutorials.jenkov.com/java-functional-programming/streams.html
	 * */

	public static void main(String[] args) {
		int count =0;
		List<Foo> list = new ArrayList<>();
		while (count <= 10 ) {
			list.add(new Foo("a"+count, "a"+count));
			count++;
		}
		list.forEach(System.out::println); 
		List<Foo> newList = list
				   .stream()
				   .filter(element -> element.getF1() != "b")
				   .map(element -> {
						   element.setF1(element.getF1().toUpperCase());
						   element.setF2(element.getF2().toUpperCase());
						   return element;
					   })
				   .collect(Collectors.toList());
		newList.forEach(System.out::println); 
	}

}
