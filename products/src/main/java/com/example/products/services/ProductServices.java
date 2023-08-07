package com.example.products.services;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.products.dtos.ProductRecordDto;
import com.example.products.models.ProductModel;
import com.example.products.repositories.ProductRepositorie;

import jakarta.transaction.Transactional;

@Service
public class ProductServices {
    @Autowired
    ProductRepositorie productRepositorie;

    public ProductModel findProductById(UUID id){
        Optional<ProductModel> product = this.productRepositorie.findById(id);
        return product.orElseThrow(() -> new RuntimeException(
            "Produto não encontrado! id: " + id
        ));
    }
    
    public List<ProductModel> findAll(){
        return productRepositorie.findAll();
    }

    @Transactional
    public ProductModel create(ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepositorie.save(productModel);
    }

    @Transactional
    public ProductModel update(UUID id, ProductRecordDto productRecordDto){
        Optional<ProductModel> product = this.productRepositorie.findById(id);
        if(product.isPresent()){
            var productModel = product.get();
            BeanUtils.copyProperties(productRecordDto, productModel);
            return productRepositorie.save(productModel);
        }else {
            throw new RuntimeException("Produto não encontrado! id: " + id);
        }
    }

    public void delete(UUID id){
        Optional<ProductModel> product = productRepositorie.findById(id);
        if(product.isPresent()){
            var productModel = product.get();
            productRepositorie.delete(productModel);
        }else {
            throw new RuntimeException("Produto não encontrado! id: " + id);
        }
    }
}
