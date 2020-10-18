package intertwinedbasicandoauth2ssso.model;


import java.io.Serializable;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import org.dozer.Mapping;
import com.fasterxml.jackson.annotation.JsonProperty;

@FieldsValueMatch.List({ 
	@FieldsValueMatch(field = "password", 
					fieldMatch = "verifyPassword", 
					message = "Passwords do not match!"),
	@FieldsValueMatch(field = "email",    // despite email field doexn't exist, it's meant for understanding.
					fieldMatch = "verifyEmail", 
					message = "Email addresses do not match!") })
public class UserEmployeeModel implements Serializable{
	
	private static final long serialVersionUID = 42L;

	@JsonProperty("id")
	private Long id;
	
	@NotBlank(message = "name must not be blank")
	@JsonProperty("username")
    private String username;
	
	@JsonProperty("password")
    private String password;
	
	@JsonProperty("repeatPassword")
    private String repeatPassword;

	
	@JsonProperty("email")
    private String email;
	
	@JsonProperty("imageUrl")
    private String imageUrl;
		
	@JsonProperty("privileges")
    private Set<Privilege> privileges;
	
	@JsonProperty("organization")
    private Organization organization;
	
	@JsonProperty("role")
    private Role role;
	
	@JsonProperty("provider")
	private AuthProvider provider;

	@JsonProperty("providerId")
    private String providerId;
    
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
	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	} 

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public AuthProvider getProvider() {
		return provider;
	}

	public void setProvider(AuthProvider provider) {
		this.provider = provider;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	@Override
	public String toString() {
		return "UserEmployeeModel [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", imageUrl=" + imageUrl + ", privileges=" + privileges + ", organization=" + organization + ", role="
				+ role + ", provider=" + provider + ", providerId=" + providerId + "]";
	}

}
