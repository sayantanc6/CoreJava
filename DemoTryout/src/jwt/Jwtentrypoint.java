package jwt;

import io.jsonwebtoken.SignatureException;

public class Jwtentrypoint {  
	
	/*
	 * source of information :-
	 * https://stormpath.com/blog/beginners-guide-jwts-in-java
	 * https://www.baeldung.com/java-json-web-tokens-jjwt
	 * */

	public static void main(String[] args) {
		JwtBuildAndVerify bav = new JwtBuildAndVerify();
		String jwt = bav.jwtBuild();
		boolean isverified = true;
		try {
			bav.verify(jwt);
		} catch (SignatureException e) {
			isverified = false;
			System.out.println("not verified : "+e);			
			e.printStackTrace();
		}
		
		if (isverified) {
			System.out.println("verified");
		}
	}
 
}
