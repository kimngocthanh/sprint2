package com.example.kntshop.birdie.service;

import com.example.kntshop.products.model.ProductBirdieDto;

public interface IBirdieService {
    ProductBirdieDto findByBirdie(Integer id);
}
