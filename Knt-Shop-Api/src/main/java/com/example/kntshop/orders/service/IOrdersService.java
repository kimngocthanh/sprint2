package com.example.kntshop.orders.service;

import com.example.kntshop.orders.model.IOrdersDto;

import java.util.List;

public interface IOrdersService {
    List<IOrdersDto> getAllOrders(String username);
}
