package com.autostore.user.infrastructure;


import com.autostore.user.application.port.driven.TokenGenerator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Component
public class JwtUtil implements TokenGenerator {

    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    @Value("${jwt.secret.key}")
    private String SECRET;

    public String generateTokenWithCpf(String cpf) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(cpf)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
    }

}
