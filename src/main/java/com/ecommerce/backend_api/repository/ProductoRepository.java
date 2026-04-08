package com.ecommerce.backend_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend_api.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long>{
    
    Optional<Producto> findByNombre(String nombre);
}
