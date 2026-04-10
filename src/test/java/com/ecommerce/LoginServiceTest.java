package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ecommerce.backend_api.service.LoginService;
import com.ecommerce.backend_api.service.UserService;

import jakarta.transaction.Transactional;

@SpringBootTest(classes = com.ecommerce.backend_api.BackendApiApplication.class)
@Transactional
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService; 

    @Test
    void T06_loginExitoso() {
        userService.registerUser("juan@email.com", "123");

        String token = loginService.login("juan@email.com", "123");

        assertNotNull(token);
    }

    @Test
    void T07_login_contaseñaIncorresta() {
        String email = "juan@email.com";
        String password = "incorrepto";

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }

    @Test
    void T08_login_usuarioNoRegistrado() {
        String email = "maria@email.com";
        String password = "1234";

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }

    @Test
    void T09_login_CamposVacios() {
        String email = "";
        String password = "";

        assertThrows(IllegalArgumentException.class, () -> {
            loginService.login(email, password);
        });
    }
}
