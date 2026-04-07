package com.ecommerce;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.ecommerce.service.ProductoService;

public class ProductoServiceTest {
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        productoService = new ProductoService();
    }

    @Test
    void T10_agregarProducto(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;

        boolean respuesta = productoService.registrarProducto(nombre,precio,stock);

        assertTrue(respuesta,"El registro deberia de retornar true con los datos validos");
    }

    @Test
    void T11_nombreVacio(){
        String nombre = "";
        float precio =1200;
        int stock =20;

        assertThrows(IllegalArgumentException.class, () -> {
            productoService.registrarProducto(nombre, precio, stock);
        });
    }
    
}
