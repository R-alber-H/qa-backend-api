package com.ecommerce.backend_api.dtos;

import com.ecommerce.backend_api.model.Producto;
import lombok.Data;

@Data
public class CarritoItemDTO {
    private Producto producto;
    private int cantidad;
}
