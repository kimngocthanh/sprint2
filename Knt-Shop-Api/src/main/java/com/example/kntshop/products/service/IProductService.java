package com.example.kntshop.products.service;

import com.example.kntshop.products.model.Product;
import com.example.kntshop.products.model.ProductBirdieDto;
import com.example.kntshop.products.model.ProductRacketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {
    Page<Product> findAllProduct(Pageable pageable);
    void createProductRacket(ProductRacketDto productRacketDto);
    void createProductBirdie(ProductBirdieDto productBirdieDto);
    Product productDetailById(Integer id);
    Page<Product> getProductByName(String name, Pageable pageable);
    Page<ProductRacketDto> getProductRacket(Pageable pageable);
    Page<ProductBirdieDto> getProductBirdie(Pageable pageable);
}
