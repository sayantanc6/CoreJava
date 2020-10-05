package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class MyEmployeeDetails extends DefaultOAuth2UserService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	UserEmployee emp;
	
	@Autowired
	User user;
	
	@Autowired
    private HttpServletRequest request;
	
	@Autowired
    private LoginAttemptService loginAttemptService;
	
    private Map<String, User> roles = new HashMap<>();

    @PostConstruct
    public void init() {
        roles.put("admin2", new User("admin", "{noop}admin1", getAuthority("ROLE_ADMIN")));
        roles.put("user2", new User("user", "{noop}user1", getAuthority("ROLE_USER")));
    }
    
    
    
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuthuser = super.loadUser(userRequest);
		Map<String, Object> attributes = oAuthuser.getAttributes();
		UserEmployee employee = new UserEmployee();
		if (userRequest.getClientRegistration().getRegistrationId().equalsIgnoreCase(AuthProvider.google.toString())) {
			employee.setId((Long)attributes.get("sub"));
		}
		employee.setId((Long)attributes.get("id")); 
		employee.setEmail((String)attributes.get("email"));
		employee.setUsername((String)attributes.get("name")); 
		employee.setProviderID(userRequest.getClientRegistration().getRegistrationId()); 
		userRepo.save(employee);
		Set<GrantedAuthority> authorities = new HashSet<>(oAuthuser.getAuthorities()); 
		String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
		return new DefaultOAuth2User(authorities, attributes, userNameAttributeName);
	}



	@Override
	public UserDetails loadUserByUsername(String username) {
        final String ip = getClientIP();
        
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        
		emp = userRepo.findByUsername(username);
		if (emp == null) {
			throw new UsernameNotFoundException(username); 
			}
		return new User(emp.getUsername(), emp.getPassword(), emp.isEnabled(), true, true, true, getAuthorities(emp.getRoles()));
	//	return new MyEmployeePrincipal(emp);
	}
	
	private final Collection<? extends GrantedAuthority> getAuthorities(Collection<Role> roles) {
    	List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    	for (Role role: roles) {
    		authorities.add(new SimpleGrantedAuthority(role.getName()));
    		authorities.addAll(role.getPrivileges()
    				.stream()
    				.map(p -> new SimpleGrantedAuthority(p.getName()))
    				.collect(Collectors.toList()));
    	}
        return authorities;
    }
	
	private List<GrantedAuthority> getAuthority(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
	
	private String getClientIP() {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return request.getRemoteAddr();
    }
}