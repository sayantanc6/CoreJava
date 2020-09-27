package inheritancemapping;

import java.lang.reflect.Field;

public class ModelMapper {
	
	public Object withoutModelMapper(Object sub,Object sup,Object mapped)  throws InstantiationException,IllegalAccessException{
		Field[] mapFields = mapped.getClass().getDeclaredFields();
		Field[] subFields = sub.getClass().getDeclaredFields();
		Field[] supFields = sup.getClass().getDeclaredFields(); 

		for (int i = 0; i < subFields.length; i++) {
			Field subfield = subFields[i];
			subfield.setAccessible(true); 
			System.out.println(subfield.getName());
			for (int j = 0; j < mapFields.length; j++) {
				Field mapfield = mapFields[j];
				mapfield.setAccessible(true); 
				 if (subfield.getName() == mapfield.getName()) {
					 mapfield.set(mapped, subfield.get(sub)); 
				}
			}
		} 
		
		for (int i = 0; i < supFields.length; i++) {
			Field supfield = supFields[i];
			supfield.setAccessible(true); 
			 System.out.println(supfield.getName()); 
			 for (int j = 0; j < mapFields.length; j++) {
				Field mapfield = mapFields[j];
				mapfield.setAccessible(true); 
				if (supfield.getName() == mapfield.getName()) {
					mapfield.set(mapped, supfield.get(sup)); 
				}
			}
		}
		return null;
	}
}
