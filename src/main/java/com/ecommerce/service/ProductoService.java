package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Producto;

public class ProductoService {
    private List<Producto> productos = new ArrayList<>();

    public boolean registrarProducto(String nombre, float precio, int stock) {

        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Campos requeridos");
        }
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (precio == 0) {
            throw new IllegalArgumentException("El precio deb ser mayor a 0");
        }

        productos.add(new Producto(nombre, precio, stock));
        return true;
    }

    public Producto buscarProducto(String nombre) {
        Producto producto = productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null); 
        return producto;
    }

}
