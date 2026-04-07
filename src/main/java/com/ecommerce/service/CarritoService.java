package com.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.ecommerce.model.Producto;

public class CarritoService {
    private List<Producto> productos = new ArrayList<>();
    private float total =0;

    public List<Producto> getProductos() {
        return productos;
    }

    public float getTotal() {
        return total;
    }

}
