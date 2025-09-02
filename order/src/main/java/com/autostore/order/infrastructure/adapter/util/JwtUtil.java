package com.autostore.order.infrastructure.adapter.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class JwtUtil {

    @Value("${jwt.secret.key}")
    private String SECRET;

    public String extractUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}
