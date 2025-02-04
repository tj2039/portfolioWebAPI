package com._2je7.pofol.Config.JWT;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil {

    private static final String secret = "inplab-apiAuth-secretKey-!@#$%^&*()";
    
    @Value("${api.access.token.expire}")
    private Integer accessTokenExpire;
    
    @Value("${api.refresh.token.expire}")
    private Integer refreshTokenExpire;
    
    public String getIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getId);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Key getSigninKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);        
        return Keys.hmacShaKeyFor(keyBytes);
      }
    
    private Claims getAllClaimsFromToken(String token) {
    	return Jwts.parserBuilder().setSigningKey(getSigninKey(secret)).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(String id) {
        return generateToken(id, new HashMap<>());
    }

    public String generateToken(String id, Map<String, Object> claims) {
        return doGenerateToken(id, claims);
    }

    public String generateRefreshToken() {
        return doRefreshToken(new HashMap<>());
    }
    
    private String doGenerateToken(String id, Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setId(id)
                .setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (accessTokenExpire * (60 * 60 * 1000))))
                .signWith(getSigninKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }

    private String doRefreshToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (refreshTokenExpire * (60 * 60 * 1000))))
                .signWith(getSigninKey(secret), SignatureAlgorithm.HS256)
                .compact();
    }
    
    public Boolean validateToken(String token, UserDetails member) {
    	String fullId = getIdFromToken(token);
    	final String id = fullId;
        return (id.equals(member.getUsername())) && !isTokenExpired(token);
    }

}