package intertwinedbasicandoauth2ssso.controller;

import java.net.URI;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
 
@RestController
public class UserController {
	
	@Autowired
	DozerBeanMapper mapper;
	
	@Autowired
	JdbcUserDetailsManager jdbcUserDetailsManager;

	@Autowired
	PasswordEncoder passwordEncoder; 
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	ApplicationEventPublisher publisher;
	
	private static final String authorizationRequestBaseUri = "oauth2/authorize-client";
    Map<String, String> oauth2AuthenticationUrls = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;
    
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;
    
    @GetMapping("/oauth_login")
    public String getLoginPage(Model model) {
        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
            .as(Iterable.class);
        if (type != ResolvableType.NONE && ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration -> oauth2AuthenticationUrls.put(registration.getClientName(), authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        return "oauth_login";
    }
    
    @PostMapping("/signup")
    public void registerUser(@Valid @RequestBody EmployeeModel model) throws BadRequestException{
        if(repo.existsByEmail(model.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        model.setPassword(passwordEncoder.encode(model.getPassword()));  
        // Creating user's account
        UserEmployee result = repo.save(mapper.map(model, UserEmployee.class));
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();
        
        System.out.println(location);

    }

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
	
	@PreAuthorize("isMember(#username)") 
	@PostMapping(value = "/sinup/{username}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE,headers = "Accept=application/json")
	public void signup(@RequestBody UserEmployeeModel model,HttpServletRequest request, @PathVariable("username") String username) {
        final String appUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        publisher.publishEvent(new OnRegistrationCompleteEvent(null, appUrl, request.getLocale(), model)); 
	}
}
