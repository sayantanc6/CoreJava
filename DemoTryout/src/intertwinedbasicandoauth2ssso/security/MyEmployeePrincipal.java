package intertwinedbasicandoauth2ssso.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyEmployeePrincipal implements UserDetails {
	 
	UserEmployee emp;
	
	public MyEmployeePrincipal(UserEmployee empDetails) {
		this.emp = empDetails;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
        final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (final Privilege privilege : emp.getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return authorities;
	}

	@Override
	public String getPassword() {
		return emp.getPassword();
	}

	@Override
	public String getUsername() {
		return emp.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public UserEmployee getUserEmployee() {
        return emp;
    }

}
