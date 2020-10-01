package intertwinedbasicandoauth2ssso.config;

import org.dozer.DozerBeanMapper; 
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;
import org.springframework.beans.factory.annotation.Autowired;

public class DTOConfigurer extends BeanMappingBuilder {
	
	@Autowired
	DozerBeanMapper mapper;

	@Override
	protected void configure() {
		mapping(UserEmployee.class, UserEmployeeModel.class, TypeMappingOptions.wildcard(false));
	}

}
