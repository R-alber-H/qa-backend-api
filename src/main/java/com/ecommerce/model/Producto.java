package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, etc.
@NoArgsConstructor // Genera constructor vacío
@AllArgsConstructor
public class Producto {
    private String nombre;
    private float precio;
    private int stock;
}
