package reflection;

import java.lang.reflect.Field;

public class Split {

	public static void main(String[] args) throws IllegalArgumentException,IllegalAccessException{
		AB ab = new AB("s1", "s2", 4, 5);
		A a = new A();
		B b = new B();

		Field[] aFields = a.getClass().getDeclaredFields();
		Field[] bFields = b.getClass().getDeclaredFields();
		Field[] abFields = ab.getClass().getDeclaredFields();
		System.out.println(ab);
		
		for (Field abf : abFields) {
			abf.setAccessible(true);
			for (Field af : aFields) {
				af.setAccessible(true);
				if (abf.getName() == af.getName()) 
					af.set(a, abf.get(ab)); 
			}
			for (Field bf : bFields) {
				bf.setAccessible(true);
				if (abf.getName() == bf.getName()) 
					bf.set(b, abf.get(ab));
			}
		}
		System.out.println(a);
		System.out.println(b);
	}
}
