package com.ecommerce.service;

public class ProductoService {
    
    public boolean registrarProducto(String nombre,float precio,int stock){

        if(nombre.isBlank()){
            throw new IllegalArgumentException("Campos requeridos");
        }
        return true;
    }
}
