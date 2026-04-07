package com.ecommerce;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ecommerce.service.UserService;
import static org.junit.jupiter.api.Assertions.*;

class RegisterServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Se ejecuta antes de cada test — crea una instancia limpia
        userService = new UserService();
    }

    @Test
    void T01_registroExitoso() {
        // GIVEN — datos válidos
        String email = "juan@mail.com";
        String password = "1234";

        // WHEN — llamamos al método
        boolean resultado = userService.registerUser(email, password);

        // THEN — esperamos true
        assertTrue(resultado, "El registro debería retornar true con datos válidos");
    }

    @Test
    void T02_emailVacio() {
        // GIVEN
        String email = "";
        String password = "1234";

        // WHEN - THEN
        assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(email, password);
        });
    }
}