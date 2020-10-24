package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import org.dozer.DozerBeanMapper;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;


@Configuration
@Import({MethodSecurityConfig.class,SpringSecurityConfig.class})
public class AppConfiguration implements AsyncConfigurer{
	
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
	 * https://github.com/callicoder/spring-boot-react-oauth2-social-login-demo
	 * https://grobmeier.solutions/spring-security-5-jwt-basic-auth.html
	 * https://github.com/karlkyck/spring-boot-completablefuture
	 * https://github.com/bytestree/spring-data-jpa-projections
	 * https://www.bytestree.com/spring/spring-data-jpa-projections-5-ways-return-custom-object/
	 * */

	
     static final String TASK_EXECUTOR_REPOSITORY  = "serviceTaskExecutor-";
    private static final String TASK_EXECUTOR_NAME_PREFIX_REPOSITORY = "serviceTaskExecutor-";

	@Bean
	public DozerBeanMapper  mapper() {
		return new DozerBeanMapper();
	}
	
	@Bean(name = TASK_EXECUTOR_REPOSITORY)
    public Executor getRepositoryAsyncExecutor() {
        return newTaskExecutor(TASK_EXECUTOR_NAME_PREFIX_REPOSITORY);
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
	
	@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors(); 
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<ClientHttpRequestInterceptor>();
        }
        interceptors.add(new AddBearerTokenHeaderInterceptor());
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }
	
	@Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }
	
	private Executor newTaskExecutor(final String taskExecutorNamePrefix) {
        final ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(10000);
        executor.setThreadNamePrefix(taskExecutorNamePrefix);
        return executor;
    }
}
