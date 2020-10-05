package intertwinedbasicandoauth2ssso.interceptors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public class AddBearerTokenHeaderInterceptor implements ClientHttpRequestInterceptor {
	
	@Autowired
	private OAuth2AuthorizedClientService  clientService;

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
			String clientRegistrationId = oauthToken.getAuthorizedClientRegistrationId();
			if (clientRegistrationId.equals(AuthProvider.facebook.toString()) ||  
				clientRegistrationId.equals(AuthProvider.github.toString()) ||
				clientRegistrationId.equals(AuthProvider.google.toString())) {
				OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, oauthToken.getName());
				request.getHeaders().add("Bearer", client.getAccessToken().getTokenValue());
			}
		}
		return null;
	}
}
