package com.ecommerce.backend_api.dtos;

import lombok.Data;

@Data
public class ProductoDTO {
    private String nombre;
    private float precio;
    private int stock;
}
