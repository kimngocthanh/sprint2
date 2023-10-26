package com.example.kntshop.orders_detail.service;

import com.example.kntshop.orders_detail.model.IOrderDetailDto;

import java.util.List;

public interface IOrderService {
    List<IOrderDetailDto> getOrderDetail(String username,String code);
}
