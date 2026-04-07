package com.ecommerce.service;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

public class LoginService {

    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();
    private Map<String, String> usuarios = new HashMap<>(Map.of(
            "juan@email.com", "1234",
            "Pedro@email.com", "123",
            "Lucía@email.com", "123"));

    public String login(String email, String password) {

        if(email.isBlank() || password.isBlank()){
            throw new IllegalArgumentException("Campos requeridos");
        }

        if(!usuarios.containsKey(email)){
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        String passwordEncontrado = usuarios.get(email);

        if(passwordEncontrado == null || !password.equals(passwordEncontrado)){
            throw new IllegalArgumentException("Credenciales incorreptas");
        }

        String token = crearToken(email);
        return token;
    }

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
