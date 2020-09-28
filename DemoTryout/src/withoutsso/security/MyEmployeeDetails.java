package com.example.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyEmployeeDetails implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;
	
	UserEmployee emp;
	
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
	public UserDetails loadUserByUsername(String username) {
        final String ip = getClientIP();
        
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
        
		emp = userRepo.findByUsername(username);
		if (emp == null) {
			throw new UsernameNotFoundException(username);
			}
		
		return new MyEmployeePrincipal(emp);
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
