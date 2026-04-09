package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ecommerce.backend_api.model.Producto;
import com.ecommerce.backend_api.service.CarritoService;
import com.ecommerce.backend_api.service.ProductoService;
import jakarta.transaction.Transactional;

@SpringBootTest(classes = com.ecommerce.backend_api.BackendApiApplication.class)
@Transactional
public class CarritoServiceTest {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Test
    void T15_carritoVacio() {
        Long carritoId = carritoService.crearCarrito();
    
        assertTrue(carritoService.getItems(carritoId).isEmpty(), "La lista de productos debería estar vacía");
        assertEquals(0, carritoService.getTotal(carritoId), "El total debería ser cero al iniciar");
    }

    @Test
    void T16_agregarProducto(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        int cantidad= 5;

        Long carritoId = carritoService.crearCarrito();
        productoService.registrarProducto(nombre, precio, stock);
        Producto producto = productoService.buscarProducto(nombre);
    
        carritoService.agregarItem(carritoId, producto, cantidad);
        assertFalse(carritoService.getItems(carritoId).isEmpty(), "La lista de productos no debería estar vacía");
    }

    @Test
    void T17_eliminarItem(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        int cantidad= 5;

        Long carritoId = carritoService.crearCarrito();

        productoService.registrarProducto(nombre, precio, stock);
        Producto producto = productoService.buscarProducto(nombre);

        carritoService.agregarItem(carritoId,producto,cantidad);

        carritoService.eliminarItem(carritoId,producto);

        assertTrue(carritoService.getItems(carritoId).isEmpty(), "La lista de productos debería estar vacía");
    }

    @Test
    void T18_calcularTotal(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        int cantidad = 5;

        Long carritoId = carritoService.crearCarrito();

        productoService.registrarProducto(nombre, precio, stock);
        Producto producto = productoService.buscarProducto(nombre);

        carritoService.agregarItem(carritoId,producto,cantidad);

        String nombre_2 = "Laptop I3";
        float precio_2 =1500;
        int stock_2 =20;
        int cantidad_2 =2;

        productoService.registrarProducto(nombre_2, precio_2, stock_2);

        Producto producto_2 = productoService.buscarProducto(nombre_2);

        carritoService.agregarItem(carritoId,producto_2,cantidad_2);

        assertEquals(9000, carritoService.getTotal(carritoId), "El total no coincide con la suma de los productos");
    }

    @Test
    void T19_agregarProductoNoRegistrado(){
        String nombre = "Laptop";
        float precio =1200;
        int stock =20;
        int cantidad =2;

        productoService.registrarProducto(nombre, precio, stock);

        Producto productoNoRegistrado = productoService.buscarProducto("Laptop I3");

        Long carritoId = carritoService.crearCarrito();

        assertThrows(IllegalArgumentException.class,() ->{
            carritoService.agregarItem(carritoId,productoNoRegistrado,cantidad);
        });
    }
}
