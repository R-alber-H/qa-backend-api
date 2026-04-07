package com.ecommerce.service;

public class ProductoService {
    
    public boolean registrarProducto(String nombre,float precio,int stock){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("Campos requeridos");
        }
        if(precio < 0){
            throw new IllegalArgumentException("El precio no puede ser negativo");
        }
        if(precio == 0){
            throw new IllegalArgumentException("El precio deb ser mayor a 0");
        }
        return true;
    }
}
