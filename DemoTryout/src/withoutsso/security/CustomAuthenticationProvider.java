package withoutsso.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	MyEmployeeDetails userDetailsService;
	

	public CustomAuthenticationProvider(UserRepository userRepository, MyEmployeeDetails userDetailsService) {
		super();
		this.userRepository = userRepository;
		this.setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		final UserEmployee user = userRepository.findByEmail(authentication.getName());
        if ((user == null)) {
            throw new BadCredentialsException("Invalid username or password");
        }
        final Authentication result = super.authenticate(authentication);
        return new UsernamePasswordAuthenticationToken(user, result.getCredentials(), result.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
