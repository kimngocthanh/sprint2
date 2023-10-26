package com.example.kntshop.orders.service;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.account_users.repository.IAccountUserRepository;
import com.example.kntshop.orders.model.IOrdersDto;
import com.example.kntshop.orders.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService implements IOrdersService{
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @Override
    public List<IOrdersDto> getAllOrders(String username) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(username);
        return ordersRepository.getAllOrders(accountUser.getId());
    }
}
