package br.com.raphaelsantos.auth_service.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.raphaelsantos.auth_service.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private int expiration;
    
    public String generateToken(AppUser user) {

        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + expiration
                ))
            .signWith(getKey())
            .compact();
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(secretKey)
        );
    }
}
