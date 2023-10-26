package com.example.kntshop.birdie.controller;

import com.example.kntshop.birdie.service.IBirdieService;
import com.example.kntshop.products.model.ProductBirdieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class BirdieController {
    @Autowired
    private IBirdieService birdieService;

    @GetMapping("/birdie/{id}")
    public ResponseEntity<ProductBirdieDto> findBirdieDto(@PathVariable int id){
        ProductBirdieDto productBirdieDto = birdieService.findByBirdie(id);
        return new ResponseEntity<>(productBirdieDto,HttpStatus.OK);
    }
}
