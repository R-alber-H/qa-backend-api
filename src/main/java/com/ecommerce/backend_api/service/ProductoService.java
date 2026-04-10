package com.ecommerce.backend_api.service;

import org.springframework.stereotype.Service;

import com.ecommerce.backend_api.model.Producto;
import com.ecommerce.backend_api.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public boolean registrarProducto(String nombre, float precio, int stock) {

        if (nombre.isBlank()) {
            throw new IllegalArgumentException("Campos requeridos");
        }
        if (precio <= 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if (precio == 0) {
            throw new IllegalArgumentException("El precio deb ser mayor a 0");
        }

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        productoRepository.save(producto);

        return true;
    }

    public Producto buscarProducto(String nombre) {
        return productoRepository.findByNombre(nombre).orElse(null);
    }

}
