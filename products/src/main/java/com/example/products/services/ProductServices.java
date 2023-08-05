package com.example.products.services;

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

    @Transactional
    public ProductModel create(ProductRecordDto productRecordDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return productRepositorie.save(productModel);
    }


}
