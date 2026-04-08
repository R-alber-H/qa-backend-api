package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Carrito;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long>  {

}
