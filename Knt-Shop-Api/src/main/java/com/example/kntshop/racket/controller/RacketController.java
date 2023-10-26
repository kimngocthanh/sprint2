package com.example.kntshop.racket.controller;

import com.example.kntshop.products.model.ProductRacketDto;
import com.example.kntshop.racket.service.IRacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RacketController {
    @Autowired
    private IRacketService racketService;

    @GetMapping("/racket/{id}")
    public ResponseEntity<ProductRacketDto> getRacketById(@PathVariable Integer id){
        ProductRacketDto productRacketDto = racketService.findRacketById(id);
        return new ResponseEntity<>(productRacketDto, HttpStatus.OK);
    }
}
