package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ecommerce.service.CarritoService;

public class CarritoServiceTest {
    private CarritoService carritoService;

    @BeforeEach
    void setup() {
        carritoService = new CarritoService();
    }

    @Test
    void T15_carritoVacio() {
        assertTrue(carritoService.getProductos().isEmpty(), "La lista de productos debería estar vacía");
        assertEquals(0, carritoService.getTotal(), "El total debería ser cero al iniciar");
    }
}
