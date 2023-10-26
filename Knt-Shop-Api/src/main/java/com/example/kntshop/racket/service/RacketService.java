package com.example.kntshop.racket.service;

import com.example.kntshop.products.model.ProductRacketDto;
import com.example.kntshop.racket.repository.IRacketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RacketService implements IRacketService{
    @Autowired
    private IRacketRepository racketRepository;
    @Override
    public ProductRacketDto findRacketById(Integer id) {
        return racketRepository.findRacketById(id);
    }
}
