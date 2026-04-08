package com.ecommerce.backend_api.service;

import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import com.ecommerce.backend_api.model.User;
import com.ecommerce.backend_api.repository.UserRepository;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private static final SecretKey KEY = Jwts.SIG.HS256.key().build();

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String login(String email, String password) {

        if (email.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException("Campos requeridos");
        }

        if (!userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        User user = userRepository.findByEmail(email);

        String passwordEncontrado = user.getPassword();

        if (passwordEncontrado == null || !password.equals(passwordEncontrado)) {
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
