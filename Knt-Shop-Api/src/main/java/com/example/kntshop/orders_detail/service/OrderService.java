package com.example.kntshop.orders_detail.service;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.account_users.repository.IAccountUserRepository;
import com.example.kntshop.orders_detail.model.IOrderDetailDto;
import com.example.kntshop.orders_detail.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService implements IOrderService{
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @Override
    public List<IOrderDetailDto> getOrderDetail(String username,String code) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(username);
        List<IOrderDetailDto> orderDetailDtoList = orderDetailRepository.getAllOrderDetail(accountUser.getId(),code);
        return orderDetailDtoList;
    }
}
