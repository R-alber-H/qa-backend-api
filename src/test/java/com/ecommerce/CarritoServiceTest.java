package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ecommerce.model.Producto;
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

    @Test
    void T16_agregarProducto(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        Producto producto = new Producto(nombre,precio,stock);

        carritoService.agregarProducto(producto);
        assertFalse(carritoService.getProductos().isEmpty(), "La lista de productos no debería estar vacía");
    }

    @Test
    void T17_eliminarProducto(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        Producto producto = new Producto(nombre,precio,stock);

        carritoService.agregarProducto(producto);
        carritoService.eliminarProducto(nombre);
        assertTrue(carritoService.getProductos().isEmpty(), "La lista de productos debería estar vacía");
    }

    @Test
    void T18_calcularTotal(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        Producto producto = new Producto(nombre,precio,stock);
        carritoService.agregarProducto(producto);

        String nombre_2 = "Laptop I3";
        float precio_2 =1500;
        int stock_2 =20;
        Producto producto_2 = new Producto(nombre_2,precio_2,stock_2);
        carritoService.agregarProducto(producto_2);

        assertEquals(2700, carritoService.getTotal(), "El total no coincide con la suma de los productos");
    }
}
