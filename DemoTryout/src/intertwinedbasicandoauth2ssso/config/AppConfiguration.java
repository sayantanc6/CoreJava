package intertwinedbasicandoauth2ssso.config;

import javax.sql.DataSource;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class AppConfiguration {
	
	/*
	 * source of information :- 
	 * https://www.baeldung.com/spring-security-method-security
	 * https://www.javainterviewpoint.com/spring-security-jdbcuserdetailsmanager-example/
	 * https://github.com/eugenp/tutorials/tree/master/spring-security-modules
	 * https://dzone.com/articles/spring-security-authentication
	 * https://stackoverflow.com/questions/32244745/how-to-add-new-user-to-spring-security-at-runtime
	 * https://www.baeldung.com/spring_redirect_after_login
	 * https://www.baeldung.com/spring-security-session
	 * https://www.baeldung.com/spring-security-remember-me
	 * https://www.baeldung.com/spring-security-logout
	 * https://www.baeldung.com/spring-events
	 * https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
	 * https://github.com/Baeldung/spring-security-registration/blob/master/src/main/java/com/baeldung/web/controller/RegistrationController.java
	 * https://www.baeldung.com/spring-security-5-oauth2-login
	 * https://github.com/eugenp/tutorials/tree/master/spring-5-security-oauth
	 * https://stackoverflow.com/questions/50061662/spring-boot-basic-authentication-and-oauth2-in-same-project
	 * https://github.com/TwinProduction/spring-security-oauth2-client-example/tree/master/custom-userservice-sample
	 * */
	
	@Bean
	public DozerBeanMapper  mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource dataSource =  new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/mydb");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	 public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	     return new MySimpleUrlAuthenticationSuccessHandler();
	 }
	
	@Bean
	public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        final SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return simpleApplicationEventMulticaster;
    }
	
}
