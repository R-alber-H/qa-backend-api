package com.ecommerce;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.service.LoginService;


public class LoginServiceTest {
    
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        loginService = new LoginService();
    }

    @Test
    void T06_loginExitoso(){
        String email = "juan@email.com";
        String password = "1234";

        String token = loginService.login(email,password);
        assertNotNull(token, "El token no debería ser nulo");
    }

    @Test
    void T07_login_contaseñaIncorresta(){
        String email = "juan@email.com";
        String password = "incorrepto"; 

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }

    @Test
    void T08_login_usuarioNoRegistrado(){
        String email = "maria@email.com";
        String password = "1234";

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }

    @Test
    void T09_login_CamposVacios(){
        String email = "";
        String password = "";

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }
}
