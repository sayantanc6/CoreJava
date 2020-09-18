package reflection;

import java.lang.reflect.Field;

public class Merge {

	public static void main(String[] args) throws IllegalArgumentException,IllegalAccessException{
		A a = new A("s1", "s2");
		B b = new B(4, 5);
		AB ab = new AB();
		Field[] aFields = a.getClass().getDeclaredFields();
		Field[] bFields = b.getClass().getDeclaredFields();
		Field[] abFields = ab.getClass().getDeclaredFields();
		System.out.println(a);
		System.out.println(b); 
		
		for (Field abf : abFields) {
			abf.setAccessible(true);
			for (Field af : aFields) {
				af.setAccessible(true);
				if (abf.getName() == af.getName()) 
					abf.set(ab, af.get(a)); 
			}
			for (Field bf : bFields) {
				bf.setAccessible(true);
				if (abf.getName() == bf.getName()) 
					abf.set(ab, bf.get(b));
			}
		}
		System.out.println(ab);
	}
	
}
