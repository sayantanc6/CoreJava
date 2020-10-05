package intertwinedbasicandoauth2ssso.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	@Value("${jwt.secret}")
    private String jwtSecret; 
	
    @Value("${jwt.issuer}")
    private String jwtIssuer;
    
    @Value("${jwt.type}")
    private String jwtType;
    
    @Value("${jwt.audience}")
    private String jwtAudience;
    
    @Autowired
    AdminRepository adminrepo; 
    
    
	private static final Logger log = LoggerFactory.getLogger(JwtAuthorizationFilter.class); 


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,String jwtAudience, String jwtIssuer, String jwtSecret, String jwtType) {
        super(authenticationManager);
        this.jwtAudience = jwtAudience;
        this.jwtIssuer = jwtIssuer;
        this.jwtSecret = jwtSecret;
        this.jwtType = jwtType;
    }
    
    private UsernamePasswordAuthenticationToken parseToken(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            String claims = token.replace("Bearer ", "");
            try {
                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(jwtSecret.getBytes())
                        .parseClaimsJws(claims);

                String username = claimsJws.getBody().getSubject();

                if ("".equals(username) || username == null) {
                    return null;
                } 

                List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
                authorities.addAll(adminrepo.findById(username).get()
                					.getPrivileges()
                					.stream()
                					.map(p -> new SimpleGrantedAuthority(p.getName()))
                					.collect(Collectors.toList()));

                return new UsernamePasswordAuthenticationToken(username, null, authorities);
            } catch (JwtException exception) {
                log.warn("Some exception : {} failed : {}", token, exception.getMessage());
            }
        }
        return null;
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
		UsernamePasswordAuthenticationToken authentication = parseToken(request);

	    if (authentication != null) {
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	    } else {
	        SecurityContextHolder.clearContext();
	    }

	    chain.doFilter(request, response);
	}
    
    
}
