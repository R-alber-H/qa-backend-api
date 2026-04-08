package com.ecommerce;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.backend_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.ecommerce.backend_api.BackendApiApplication.class)
@Transactional
class RegisterServiceTest {
    
    @Autowired
    private UserService userService;

    @Test
    void T01_registroExitoso() {
        String email = "juan@email.com";
        String password = "1234";

        boolean resultado = userService.registerUser(email, password);
        assertTrue(resultado, "El registro debería retornar true con datos válidos");
    }

    @Test
    void T02_emailVacio() {
        String email = "";
        String password = "1234";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(email, password);
        });
    }

    @Test
    void T03_contaseñaVacia(){
        String email = "juan@email.com";
        String password = "";

        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(email, password);
        });
    }

    @Test
    void T04_emailDuplicado(){
        String email = "juan@email.com";
        String password = "1234";
        userService.registerUser(email, password);

        assertThrows(IllegalArgumentException.class,() -> {
            userService.registerUser(email, password);
        });
    }

    @Test 
    void T05_emailSinFormato(){
        String email = "juanemail.com";
        String password = "1234";
   
        assertThrows(IllegalArgumentException.class,() -> {
            userService.registerUser(email, password);
        });
    }
}