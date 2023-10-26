package com.example.kntshop.products.service;

import com.example.kntshop.products.model.Product;
import com.example.kntshop.products.model.ProductBirdieDto;
import com.example.kntshop.products.model.ProductRacketDto;
import com.example.kntshop.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService{
    @Autowired
    private IProductRepository productRepository;
    @Override
    public Page<Product> findAllProduct(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void createProductRacket(ProductRacketDto productRacketDto) {
        productRepository.createRacket(productRacketDto);
    }

    @Override
    public void createProductBirdie(ProductBirdieDto productBirdieDto) {
        productRepository.createBirdie(productBirdieDto);
    }

    @Override
    public Product productDetailById(Integer id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Page<Product> getProductByName(String name, Pageable pageable) {
        return productRepository.getProductByName(name,pageable);
    }

    @Override
    public Page<ProductRacketDto> getProductRacket(Pageable pageable) {
        return productRepository.getAllProductRacket(pageable);
    }

    @Override
    public Page<ProductBirdieDto> getProductBirdie(Pageable pageable) {
        return productRepository.getAllProductBirdie(pageable);
    }

}
