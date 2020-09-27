package withoutsso.util;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	DozerBeanMapper mapper;

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		repo.save(mapper.map(event.getUser(), UserEmployee.class));
	}
	
	

}
