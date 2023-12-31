package lib.base.backend.modules.security.jwt.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	@Value("${app.config.security.jwt.secret.key}")
    private String SECRET_KEY;
    
	@Value("${app.config.security.jwt.expiration.time}")
	private long EXPIRATION_TIME = 86400000;

    @SuppressWarnings("deprecation")
	public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    
    public String extractToken(String authorizationHeader) {
    	
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            return token;
        }
       return null;
    }

    public boolean validateToken(String token) {
        return !extractClaims(token).getExpiration().before(new Date());
    }

    @SuppressWarnings("deprecation")
	private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
}