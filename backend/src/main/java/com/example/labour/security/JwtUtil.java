package com.example.labour.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

  @Value("${jwt.secret:labour-app-secret-key-2024-super-secret-key-production}")
  private String jwtSecret;

  @Value("${jwt.expiration:86400000}")
  private long jwtExpiration;

  public String generateToken(Long userId, String email, String name) {
    return Jwts.builder()
        .setSubject(email)
        .claim("userId", userId)
        .claim("name", name)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
        .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
        .compact();
  }

  private JwtParser getParser() {
    return Jwts.parserBuilder()
        .setSigningKey(Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8)))
        .build();
  }

  public String extractEmail(String token) {
    try {
      Jws<Claims> claimsJws = getParser().parseClaimsJws(token);
      return claimsJws.getBody().getSubject();
    } catch (Exception e) {
      return null;
    }
  }

  public Long extractUserId(String token) {
    try {
      Jws<Claims> claimsJws = getParser().parseClaimsJws(token);
      Number userId = claimsJws.getBody().get("userId", Number.class);
      return userId != null ? userId.longValue() : null;
    } catch (Exception e) {
      return null;
    }
  }

  public boolean validateToken(String token) {
    try {
      getParser().parseClaimsJws(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
