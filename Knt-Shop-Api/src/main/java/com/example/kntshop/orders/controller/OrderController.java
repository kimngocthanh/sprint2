package com.example.kntshop.orders.controller;

import com.example.kntshop.orders.model.IOrdersDto;
import com.example.kntshop.orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private IOrdersService ordersService;
    @GetMapping("/orders")
    public ResponseEntity<?> getAllOrders(@RequestParam String username) {
        List<IOrdersDto> ordersDto = ordersService.getAllOrders(username);
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
    }
}
