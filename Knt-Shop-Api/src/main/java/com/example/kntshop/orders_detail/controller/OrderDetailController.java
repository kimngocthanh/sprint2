package com.example.kntshop.orders_detail.controller;

import com.example.kntshop.orders_detail.model.IOrderDetailDto;
import com.example.kntshop.orders_detail.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/order-detail/{code}")
    public ResponseEntity<?> findAll(@RequestParam String username,@PathVariable String code){
        List<IOrderDetailDto> orderDetailDtoList = orderService.getOrderDetail(username,code);
        return new ResponseEntity<>(orderDetailDtoList, HttpStatus.OK);
    }
}
