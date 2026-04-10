package com.ecommerce.backend_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.ecommerce.backend_api.dtos.CarritoItemDTO;
import com.ecommerce.backend_api.model.CarritoItems;
import com.ecommerce.backend_api.model.Producto;
import com.ecommerce.backend_api.service.CarritoService;

import io.jsonwebtoken.lang.Collections;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping
    public ResponseEntity<String> crearCarrito() {
        try {
            Long idCarrito = carritoService.crearCarrito();
            return ResponseEntity.status(HttpStatus.CREATED).body("Carrito creado con id: " + idCarrito);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<String> agregarItem(@PathVariable Long id, @RequestBody CarritoItemDTO dto) {
        try {
            carritoService.agregarItem(id, dto.getProducto(), dto.getCantidad());
            return ResponseEntity.status(HttpStatus.OK).body("Item agregado al carrito");

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @DeleteMapping("/{id}/items/nombre")
    public ResponseEntity<String> eliminarItem(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            carritoService.eliminarItem(id, producto);
            return ResponseEntity.status(HttpStatus.OK).body("Item eliminado del carrito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el servidor");
        }
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<List<CarritoItems>> obtenerItems(@PathVariable Long id) {
        try {
            List<CarritoItems> items = carritoService.getItems(id);
            return ResponseEntity.ok(items);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @GetMapping("/{id}/total")
    public ResponseEntity<Float> obtenerTotal(@PathVariable Long id) {
        try {
            float total = carritoService.obtenerTotal(id);
            return ResponseEntity.ok(total);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(0f);
        }
    }
}
