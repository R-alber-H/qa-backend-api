package com.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.CarritoItems;

@Repository
public interface CarritoItemsRepository extends JpaRepository<CarritoItems, Long>{
    
}
