package com.example.products.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.products.dtos.ProductRecordDto;
import com.example.products.models.ProductModel;
import com.example.products.repositories.ProductRepositorie;

import jakarta.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    ProductRepositorie productRepositorie;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel); // conversao de dto para model
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepositorie.save(productModel));
    }
}
