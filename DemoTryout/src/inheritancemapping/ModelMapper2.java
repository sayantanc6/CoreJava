package inheritancemapping;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

public class ModelMapper2 {
	
	public Object withModelMapper(Object baseObject,Object mappedObject) {
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

}
