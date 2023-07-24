package com.example.products.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.products.models.ProductModel;

@Repository
public interface ProductRepositorie extends JpaRepository<ProductModel, UUID> {
    
}
