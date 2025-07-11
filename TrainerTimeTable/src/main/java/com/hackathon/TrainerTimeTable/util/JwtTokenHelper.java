package com.hackathon.TrainerTimeTable.util;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
public class JwtTokenHelper {

    private static final String SECRET_STRING = "my-Ultra-Secret-Key-That-Is-Long-Enough-To-Pass-HS512-Check-12345!!";
//    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes(StandardCharsets.UTF_8));

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; // in milliseconds
    
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_STRING.getBytes(StandardCharsets.UTF_8);
//        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
   
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, username);
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
            .compact();
    }

  
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

  
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

   
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }


  
    private boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }


    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
