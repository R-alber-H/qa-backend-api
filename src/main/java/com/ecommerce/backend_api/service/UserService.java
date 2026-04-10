package com.ecommerce.backend_api.service;

import org.springframework.stereotype.Service;

import com.ecommerce.backend_api.model.User;
import com.ecommerce.backend_api.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(String email, String password) {
        
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email requerido");
        }

        if (password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password requerido");
        }
        if (!email.contains("@")){
            throw new IllegalArgumentException("Formato invalido");
        }
        if (userRepository.existsByEmail(email)){
            throw new IllegalArgumentException("Email registrado");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password); 
        userRepository.save(newUser); 
        return true;
    }
}
