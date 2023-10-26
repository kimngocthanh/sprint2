package com.example.kntshop.racket.service;

import com.example.kntshop.products.model.ProductRacketDto;

public interface IRacketService {
    ProductRacketDto findRacketById(Integer id);
}
