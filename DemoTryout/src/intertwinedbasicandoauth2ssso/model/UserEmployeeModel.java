package intertwinedbasicandoauth2ssso.model;

import java.io.Serializable;
import java.util.Set;

import org.dozer.Mapping;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEmployeeModel implements Serializable{
	
	private static final long serialVersionUID = 42L;

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("username")
    private String username;
	
	@JsonProperty("password")
    private String password;
	
	@JsonProperty("privileges")
    private Set<Privilege> privileges;
	
	@JsonProperty("organization")
    private Organization organization;
	
	@JsonProperty("role")
    private Role role;
    
    @Mapping("id")
	public Long getId() {
		return id;
	}
    
	public void setId(Long id) {
		this.id = id;
	}
	
	@Mapping("username")
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Mapping("password")
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Privilege> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
	@Mapping("organization")
	public Organization getOrganization() {
		return organization;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	 
	@Mapping("role")
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserEmployeeModel [id=" + id + ", username=" + username + ", password=" + password + ", privileges="
				+ privileges + ", organization=" + organization + ", role=" + role + "]";
	}

}
