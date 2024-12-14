package lib.base.backend.modules.security.jwt.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	
	@Value("${app.config.security.jwt.secret.key}")
    private String secretKey;

    @SuppressWarnings("deprecation")
	public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    
    public String extractToken(String authorizationHeader) {
    	
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
       return null;
    }

    public boolean validateToken(String token) {
    	
    	LocalDateTime expirationTime = extractClaims(token).getExpiration().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    	
    	return !expirationTime.isBefore(LocalDateTime.now());
    }

    @SuppressWarnings("deprecation")
	private Claims extractClaims(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}