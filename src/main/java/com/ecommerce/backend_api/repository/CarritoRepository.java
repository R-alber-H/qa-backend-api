package com.ecommerce.backend_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend_api.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>  {
    Optional<Carrito> findById(Long id);
}
