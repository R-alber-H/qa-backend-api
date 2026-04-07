package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    // test 1
    private List<String> listaEmails = new ArrayList<>();

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
        if (listaEmails.contains(email)){
            throw new IllegalArgumentException("Email registrado");
        }
        listaEmails.add(email);
        return true;
    }
}
