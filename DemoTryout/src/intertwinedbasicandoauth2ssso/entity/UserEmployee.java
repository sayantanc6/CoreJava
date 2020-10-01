package intertwinedbasicandoauth2ssso.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity 
@Table(name = "EMPLOYEE")
public class UserEmployee {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;

	    @Column(nullable = false, unique = true)
	    private String username;

	    private String password;

	    @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(name = "users_privileges", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
	    private Set<Privilege> privileges;

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "organization_id", referencedColumnName = "id")
	    private Organization organization;
	    
	    private String email;
	    
	    private String imageUrl;

	    private String providerId;
	    
	    private boolean enabled;

	    @NotNull
	    @Enumerated(EnumType.STRING)
	    private AuthProvider provider;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

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

		public Organization getOrganization() {
			return organization;
		}

		public void setOrganization(Organization organization) {
			this.organization = organization;
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

		public String getProviderId() {
			return providerId;
		}

		public void setProviderId(String providerId) {
			this.providerId = providerId;
		}

		public boolean isEnabled() {
			return enabled;
		}

		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}

		public AuthProvider getProvider() {
			return provider;
		}

		public void setProvider(AuthProvider provider) {
			this.provider = provider;
		}
}
