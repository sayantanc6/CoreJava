package dto.inheritancemapping;

import java.lang.reflect.Field;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class InheritanceMapper {
	
	public Object inheritanceToObject(Object baseObject,Object mappedObject) {
		Field[] mappedFields = mappedObject.getClass().getDeclaredFields();
		
		ReflectionUtils.doWithFields(baseObject.getClass(), 
				new FieldCallback() {
					
					@Override
					public void doWith(Field baseField) throws IllegalArgumentException, IllegalAccessException {
						baseField.setAccessible(true); 
						for (Field mapfield : mappedFields) {
							mapfield.setAccessible(true); 
							 if (mapfield.getName() == baseField.getName()) {
								mapfield.set(mappedObject, baseField.get(baseObject)); 
							}
						}
					}
					
				});
		return mappedObject; 
	}
	
	public void objectToInheritence(Object sourceObject,Object baseObject,Object superObject) throws IllegalAccessException,IllegalArgumentException{
		Field[] sourceFields = sourceObject.getClass().getDeclaredFields();
		Field[] baseFields = FieldUtils.getAllFields(baseObject.getClass());
		Field[] superFields = FieldUtils.getAllFields(baseObject.getClass().getSuperclass());		
		for (Field sourcefield : sourceFields) {
			sourcefield.setAccessible(true);
			for (Field basefield : baseFields) {
				basefield.setAccessible(true);
				if (basefield.getName() == sourcefield.getName()) {
					basefield.set(baseObject, sourcefield.get(sourceObject));
				}
			}
			for (Field superfield : superFields) {
				superfield.setAccessible(true);
				if (superfield.getName() == sourcefield.getName()) {
					superfield.set(superObject, sourcefield.get(sourceObject));
				}
			}
		}
	}
}