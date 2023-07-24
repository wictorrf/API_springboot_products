package com.example.products.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.products.models.ProductModel;

public interface ProductRepositorie extends JpaRepository<ProductModel, UUID> {
    
}
