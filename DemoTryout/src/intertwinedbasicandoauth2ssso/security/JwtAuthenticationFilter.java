package intertwinedbasicandoauth2ssso.security;

import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Value("${jwt.secret}")
    private String jwtSecret;
	
    @Value("${jwt.issuer}")
    private String jwtIssuer;
    
    @Value("${jwt.type}")
    private String jwtType;
    
    @Value("${jwt.audience}")
    private String jwtAudience;
    
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,String jwtAudience, String jwtIssuer,String jwtSecret, String jwtType) {
            this.jwtAudience = jwtAudience;
            this.jwtIssuer = jwtIssuer;
            this.jwtSecret = jwtSecret;
            this.jwtType = jwtType;
            this.setAuthenticationManager(authenticationManager);
            setFilterProcessesUrl("/api/login");
        } 

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/login");
    }

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
	        throw new AuthenticationServiceException(
	                "Authentication method not supported: " + request.getMethod()); 
	    }

	    String username = obtainUsername(request);
	    String password = obtainPassword(request);

	    if (username == null) {
	        username = "";
	    }

	    if (password == null) {
	        password = "";
	    }

	    username = username.trim();

	    UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
	            username, password);

	    // Allow subclasses to set the "details" property
	    setDetails(request, authRequest);

	    return this.getAuthenticationManager().authenticate(authRequest);
	}


	@Override
	protected void successfulAuthentication(
	        HttpServletRequest request, HttpServletResponse response,
	        FilterChain filterChain, Authentication authentication) {
	    UserAdmin user = (UserAdmin)authentication.getPrincipal();
	    SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	    String token = Jwts.builder()
	            .signWith(secretKey, SignatureAlgorithm.HS512)
	            .setHeaderParam("typ", jwtType)
	            .setIssuer(jwtIssuer)
	            .setAudience(jwtAudience)
	            .setSubject(user.getUsername())
	            .setExpiration(new Date(System.currentTimeMillis() + 864000000))
	            .compact();

	    response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
	}
}
