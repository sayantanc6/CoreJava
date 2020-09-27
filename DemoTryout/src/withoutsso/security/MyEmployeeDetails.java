package withoutsso.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyEmployeeDetails implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	UserEmployee emp;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		emp = userRepo.findByUsername(username);
		if (emp == null) {
			throw new UsernameNotFoundException(username);
			}
		
		return new MyEmployeePrincipal(emp);
	}

}
