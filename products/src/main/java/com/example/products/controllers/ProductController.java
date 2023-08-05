package com.example.products.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.products.dtos.ProductRecordDto;
import com.example.products.models.ProductModel;
import com.example.products.services.ProductServices;

import jakarta.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    ProductServices productServices;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto) {
        ProductModel productModel = productServices.create(productRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productModel);
    }
    
    @GetMapping("/product/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        try {
            ProductModel productModel = productServices.findProductById(id);
            return ResponseEntity.status(HttpStatus.OK).body(productModel);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // @PutMapping("/product/{id}")
    // public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
    // @RequestBody @Valid ProductRecordDto productRecordDto){
    //     Optional<ProductModel> product0 = productRepositorie.findById(id);
    //     if(product0.isEmpty()){
    //          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    //     }
    //     var productModel = product0.get();
    //     BeanUtils.copyProperties(productRecordDto, productModel);
    //     return ResponseEntity.status(HttpStatus.OK).body(productRepositorie.save(productModel));
    // }

    // @DeleteMapping("/product/{id}")
    // public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){
    //     Optional<ProductModel> product0 = productRepositorie.findById(id);
    //     if(product0.isEmpty()){
    //          return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
    //     }
    //     return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully!");
    // }
}
