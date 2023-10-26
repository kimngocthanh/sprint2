package com.example.kntshop.products.controller;

import com.example.kntshop.birdie.service.IBirdieService;
import com.example.kntshop.products.model.Product;
import com.example.kntshop.products.model.ProductBirdieDto;
import com.example.kntshop.products.model.ProductRacketDto;
import com.example.kntshop.products.service.IProductService;
import com.example.kntshop.racket.service.IRacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IBirdieService birdieService;

    @Autowired
    private IRacketService racketService;

    @GetMapping("/product")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(defaultValue = "0", required = false) int page,
                                                        @RequestParam(defaultValue = "8", required = false) int size,
                                                        @RequestParam(defaultValue = "", required = false) String searchName,
                                                        @RequestParam(defaultValue = "", required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        switch (search) {
            case "searchByName":
                productPage = productService.getProductByName(searchName, pageable);
                break;
            default:
                productPage = productService.findAllProduct(pageable);
        }
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @GetMapping("/product/get-by-name")
    public ResponseEntity<?> getProductByName(@RequestParam(defaultValue = "0", required = false) int page,
                                              @RequestParam(defaultValue = "8", required = false) int size,
                                              @RequestParam(defaultValue = "", required = false) String searchName) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage;
        if (searchName.equals("")) {
            productPage = productService.findAllProduct(pageable);
            return new ResponseEntity<>(productPage, HttpStatus.OK);
        }
        productPage = productService.getProductByName(searchName, pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }

    @PostMapping("/createBirdie")
    public ResponseEntity<?> createBirdie(@RequestBody ProductBirdieDto productBirdieDto) {
        productService.createProductBirdie(productBirdieDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/createRacket")
    public ResponseEntity<?> createRacket(@RequestBody ProductRacketDto productRacketDto) {
        productService.createProductRacket(productRacketDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/productDetail/{id}")
    public ResponseEntity<Object> productDetail(@PathVariable Integer id) {
        ProductBirdieDto productBirdieDto = birdieService.findByBirdie(id);
        ProductRacketDto productRacketDto = racketService.findRacketById(id);
        if (productRacketDto == null) {
            return new ResponseEntity<>(productBirdieDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(productRacketDto, HttpStatus.OK);
        }
    }

    @GetMapping("/product-racket")
    public ResponseEntity<?> getProductRacket(@RequestParam(defaultValue = "0", required = false) int page,
                                              @RequestParam(defaultValue = "8", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductRacketDto> productPage = productService.getProductRacket(pageable);
        return new ResponseEntity<>(productPage,HttpStatus.OK);
    }

    @GetMapping("/product-birdie")
    public ResponseEntity<?> getProductBirdie(@RequestParam(defaultValue = "0", required = false) int page,
                                              @RequestParam(defaultValue = "8", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductBirdieDto> productPage = productService.getProductBirdie(pageable);
        return new ResponseEntity<>(productPage, HttpStatus.OK);
    }
}
