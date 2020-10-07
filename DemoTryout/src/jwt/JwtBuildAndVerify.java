package jwt;

import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JwtBuildAndVerify {
	
	String jwt = "";
 
	public JwtBuildAndVerify() {
		super();
	}

	public String jwtBuild() {
		
		jwt =Jwts.builder()
					  .setSubject("my subject")
					  .setExpiration(new Date(1300819380))
					  .claim("name", "Robert Token Man")
					  .claim("scope", "self groups/admins")
					  .signWith(SignatureAlgorithm.HS256, "secret".getBytes()) 
					  .compact();
		System.out.println("builded jwt : "+jwt); 
		return jwt;
	}
	
	public boolean verify(String jwt) throws SignatureException {
		Jws<Claims> claims = Jwts.parser()
								.setSigningKey("secret".getBytes())
								.parseClaimsJws(jwt);
		String scope = claims.getBody().get("scope", String.class); 
		
		return scope == "self groups/admins";
	}
}
