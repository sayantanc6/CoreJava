package intertwinedbasicandoauth2ssso.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.client.web.HttpSessionOAuth2AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.WebApplicationContext;

@Configuration 
@PropertySource("application-oauth2.properties")
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private WebApplicationContext applicationContext;
	
    private MyEmployeeDetails userDetailsService;
    
    @Autowired
	CustomOauth2UserDetailsService customOauth2userDetailsService;
    
    @Autowired
    private AuthenticationSuccessHandlerImpl successHandler;
     
    @Autowired
    private MyLogoutSuccessHandler logoutSuccessHandler;
    
    @Autowired
    AuthenticationSuccessHandler handler;
    
    @Autowired
	public DataSource dataSource;
    
    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    
    @Autowired
	OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	
	@Autowired
	OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    
    @PostConstruct
    public void completeSetup() {
        userDetailsService = applicationContext.getBean(MyEmployeeDetails.class);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
        .passwordEncoder(encoder())
        .and()
        .authenticationProvider(authenticationProvider())
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select e.email,e.password,e.enabled "
                + "from EMPLOYEE e "
                + "where e.email = ?");
		
		auth.inMemoryAuthentication()
        .withUser("user1").password("{noop}user1Pass").roles("USER")
        .and()
        .withUser("admin1").password("{noop}admin1Pass").roles("ADMIN");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
        .antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf()
        .disable()
		.authorizeRequests()
        .antMatchers("/login","/oauth_login", "/loginFailure", "/")
        .permitAll()
        .and()
        .formLogin()
        .loginPage("/login.html")
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/homepage.html", true)
        .successHandler(handler) //  to determine the URL to redirect the user to after login based on the role of the user.
        .permitAll()
        .successHandler(successHandler)
        .failureHandler(authenticationFailureHandler) 
        .failureUrl("/login.html?error=true")
        .and()
        .logout()
        .logoutSuccessHandler(logoutSuccessHandler)
        .logoutSuccessUrl("/afterlogout.html").deleteCookies("JSESSIONID") 
        .and()
        .rememberMe().rememberMeParameter("remember-me-new").key("uniqueAndSecret").tokenValiditySeconds(86400) // 1
        .and()
        .oauth2Login()
        .loginPage("/oauth_login")
        .authorizationEndpoint()
        .baseUri("/oauth2/authorize-client")
        .authorizationRequestRepository(authorizationRequestRepository())
        .and()
        .tokenEndpoint()
        .accessTokenResponseClient(accessTokenResponseClient())
        .and()
        .defaultSuccessUrl("/loginSuccess")
        .failureUrl("/loginFailure")
        .and()
        .oauth2Login()
        .redirectionEndpoint()
            .baseUri("/oauth2/callback/*")
            .and()
        .userInfoEndpoint()
            .userService(customOauth2userDetailsService)
            .and()
        .successHandler(oAuth2AuthenticationSuccessHandler)
        .failureHandler(oAuth2AuthenticationFailureHandler)
        .and()
        .sessionManagement()
        .sessionFixation().migrateSession() // 2
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .invalidSessionUrl("/invalidSession.html")
        .maximumSessions(2)
        .expiredUrl("/sessionExpired.html");
		
		/* 1
		 * token based remember me authentication.  
		 * Upon authenticating if the HTTP parameter named "remember-me"exists,
		 * then the user will be remembered even after their javax.servlet.http.HttpSession expires.  
		 * The key is a private value secret for the entire application and it will be used when generating the contents of the token.
		 * 
		 * 2
		 * Session Fixation is an attack that permits an attacker to hijack a valid user session. 
		 * The attacker has to provide a legitimate Web application session ID and try to make the victim's browser use it. 
		 * MigrateSession() is to specify that a new session should be created and the session attributes from the original HttpSession should be retained.
		 * */
	}
	@Bean(BeanIds.AUTHENTICATION_MANAGER) 
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	 @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService);
	        authProvider.setPasswordEncoder(encoder());
	        return authProvider;
	    }
	
	 @Bean
	    public PasswordEncoder encoder() {
	        return new BCryptPasswordEncoder(); 
	    }
	 
	 @Bean
	    public AuthorizationRequestRepository<OAuth2AuthorizationRequest> authorizationRequestRepository() {
	        return new HttpSessionOAuth2AuthorizationRequestRepository();
	    }

	    @Bean
	    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> accessTokenResponseClient() {
	        DefaultAuthorizationCodeTokenResponseClient accessTokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
	        return accessTokenResponseClient;
	    }
	 
	 @Bean
	    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
	        return new SecurityEvaluationContextExtension();
	    }

}
