package intertwinedbasicandoauth2ssso.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.web.servlet.LocaleResolver;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
    private LocaleResolver localeResolver;
	
	@Autowired
    private MessageSource messages;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {
		
		super.onAuthenticationFailure(request, response, exception);
        final Locale locale = localeResolver.resolveLocale(request);
        String errorMessage = messages.getMessage("message.badCredentials", null, locale);

        if (exception.getMessage().equalsIgnoreCase("User is disabled")) {
        	
                errorMessage = messages.getMessage("auth.message.disabled", null, locale);
            } else if (exception.getMessage().equalsIgnoreCase("User account has expired")) {
            	
                errorMessage = messages.getMessage("auth.message.expired", null, locale);
            } else if (exception.getMessage().equalsIgnoreCase("blocked")) {
            	
                errorMessage = messages.getMessage("auth.message.blocked", null, locale);
            } else if (exception.getMessage().equalsIgnoreCase("unusual location")) {
                errorMessage = messages.getMessage("auth.message.unusual.location", null, locale);
            }
        request.getSession().setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
	}
}