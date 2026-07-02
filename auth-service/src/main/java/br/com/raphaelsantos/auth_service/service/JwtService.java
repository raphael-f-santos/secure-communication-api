package br.com.raphaelsantos.auth_service.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import br.com.raphaelsantos.auth_service.entity.AppUser;
import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
    
    private final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();
    
    public String generateToken(AppUser user) {

        return Jwts.builder()
            .subject(user.getUsername())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + 1000 * 60 * 60
                ))
            .signWith(SECRET_KEY)
            .compact();
    }
}
