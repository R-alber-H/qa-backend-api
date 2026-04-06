package com.ecommerce;
import org.junit.jupiter.api.Test;
import com.ecommerce.service.UserService;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterTest {
    @Test
    void registroExitoso() {
        // GIVEN - datos del usuario
        String username = "juan";
        String password = "1234";

        // WHEN - llamamos al método que aún no existe
        String resultado = UserService.registerUser(username, password);

        // THEN - validamos el resultado esperado
        assertEquals("Usuario registrado", resultado);
    }
}
