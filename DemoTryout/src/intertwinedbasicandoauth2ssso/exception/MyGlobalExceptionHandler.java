package intertwinedbasicandoauth2ssso.exception;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * source of information :-
 * https://stackoverflow.com/questions/35702342/how-do-i-add-a-line-break-blank-line-after-my-log-statement
 * https://github.com/eugenp/tutorials/blob/master/spring-boot-rest/src/main/java/com/baeldung/web/error/RestResponseEntityExceptionHandler.java
 * https://bezkoder.com/spring-boot-controlleradvice-exceptionhandler/
 * https://tools.ietf.org/html/rfc7231#section-6.5.1
 * */

@RestControllerAdvice
public class MyGlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	LoginAttemptService service;
	
	private static final Logger log = LoggerFactory.getLogger(MyGlobalExceptionHandler.class); 
	
	@ExceptionHandler(value = {IOException.class,ServletException.class}) 
	public ResponseEntity<Object> resourceNotFound(Exception ex,WebRequest request) {
		if (ex instanceof IOException) {
			ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), System.currentTimeMillis(), "resource not found", "some description",request.getContextPath()); 
	        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
			return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
			
		} else if (ex instanceof ServletException) {
			ErrorMessage message = new ErrorMessage(HttpStatus.UNAUTHORIZED.value(), System.currentTimeMillis(), "unauthorized", "some description",request.getContextPath()); 
	        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
			return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
		} else {
			return null;
		} 
	}
	
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> handleBadrequest(BadRequestException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis(), "bad request", "some description",request.getContextPath()); 
        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
		return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, request); 
	}

	@ExceptionHandler(value = {AuthenticationException.class,AuthenticationServiceException.class}) 
	public ResponseEntity<Object> handleAuthenticationFailed(Exception ex, WebRequest request) {
		if (ex instanceof AuthenticationException) {
			ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN.value(), System.currentTimeMillis(), "authentication failed", "some description",request.getContextPath()); 
	        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
			return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
			
		}else if(ex instanceof AuthenticationServiceException){
			ErrorMessage message = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED.value(), System.currentTimeMillis(), ex.getMessage(), "some description",request.getContextPath()); 
	        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
			return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
		}else {
			return null; 
		}
	}
	
	@ExceptionHandler(value = ExecutionException.class)
	public ResponseEntity<Object> handleAttemptervice(ExecutionException ex, WebRequest request) {
		service.attemptsCache.put(service.key, 1); 
		ErrorMessage message = new ErrorMessage(HttpStatus.LOCKED.value(), System.currentTimeMillis(), "login failed", "some description",request.getContextPath()); 
        log.warn(" Exception handled -> \n Specific class name :  {} \n Root cause of this : {} ", ex.getClass().getName(),ex.getStackTrace());  
		return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.LOCKED, request); 
	}
	
}
