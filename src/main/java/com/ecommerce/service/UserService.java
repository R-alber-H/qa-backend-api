package com.ecommerce.service;

public class UserService {
    // test 1

    public boolean registerUser(String email, String password) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email requerido");
        }

        if (password == null || password.isEmpty()){
            throw new IllegalArgumentException("Password requerido");
        }
        return true;
    }

}
