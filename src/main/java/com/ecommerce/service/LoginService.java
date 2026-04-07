package com.ecommerce.service;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;

public class LoginService {

    public String login(String email, String password){
        String token = crearToken(email);
        return token;
    }
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public String crearToken(String email) {
        
        long tiempoExpiracion = 3600000; 
        return Jwts.builder()
                .subject(email)                 
                .issuedAt(new Date())              
                .expiration(new Date(System.currentTimeMillis() + tiempoExpiracion)) 
                .signWith(KEY)                  
                .compact();
    }
}
