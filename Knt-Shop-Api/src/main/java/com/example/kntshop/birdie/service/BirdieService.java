package com.example.kntshop.birdie.service;

import com.example.kntshop.birdie.repository.IBirdieRepository;
import com.example.kntshop.products.model.ProductBirdieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BirdieService implements IBirdieService{
    @Autowired
    private IBirdieRepository birdieRepository;
    @Override
    public ProductBirdieDto findByBirdie(Integer id) {
        return birdieRepository.findBirdieById(id);
    }
}
