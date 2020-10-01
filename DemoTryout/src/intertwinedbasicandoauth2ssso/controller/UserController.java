package withoutsso.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	ApplicationEventPublisher publisher;

	@Secured({ "ROLE_USER", "ROLE_ADMIN","ROLE_VIEWER" })
	@GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@PostAuthorize("returnObject.username == authentication.principal.nickName")
	public UserEmployee loadUserDetail(String username) {
	    return repo.findByUsername(username);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/user/{username}")
	@IsViewer
	public String checkIfUserExists(@PathVariable("username") String username)
	{
		boolean flag = jdbcUserDetailsManager.userExists(username);
		if (flag)
			return "\"" + username + "\" exist in Database";
		else
			return "\"" + username + "\" does not exist in Database";
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@Secured("ROLE_ADMIN")
	@PostMapping("/user/{username}/{password}/{role}")
	public String createUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("role") String role)
	{
		jdbcUserDetailsManager.createUser(
				User.withUsername(username).password(passwordEncoder.encode(password)).roles("ROLE_USER").build());
		return checkIfUserExists(username);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/user/{username}")
	public String deleteUser(@PathVariable("username") String username)
	{
		jdbcUserDetailsManager.deleteUser(username);
		return checkIfUserExists(username);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@Secured("ROLE_ADMIN")
	@PutMapping("/user/{username}/{password}/{role}")
	public String updateUser(@PathVariable("username") String username, @PathVariable("password") String password,
			@PathVariable("role") String role)
	{
		jdbcUserDetailsManager.updateUser(
				User.withUsername(username).password(passwordEncoder.encode(password)).roles("ROLE_USER").build());
		return checkIfUserExists(username);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@PreFilter("filterObject != authentication.principal.username")
	@GetMapping("/user/{username}")
	public String joinUsernames(List<String> usernames) {
	    return usernames.stream().collect(Collectors.joining(";"));
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@PostFilter("filterObject != authentication.principal.username")
	public List<String> getAllUsernamesExceptCurrent() {
	    return repo.findAll()
	    		.stream().map(user -> user.getUsername()).collect(Collectors.toList());
	}
	
	@PostMapping(value = "/sinup",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public void signup(@RequestBody UserEmployeeModel model,HttpServletRequest request) {
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        publisher.publishEvent(new OnRegistrationCompleteEvent(null, appUrl, request.getLocale(), model)); 
	}
}
