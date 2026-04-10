package com.ecommerce.backend_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.backend_api.model.CarritoItems;

@Repository
public interface CarritoItemsRepository extends JpaRepository<CarritoItems, Long>{
   
}
