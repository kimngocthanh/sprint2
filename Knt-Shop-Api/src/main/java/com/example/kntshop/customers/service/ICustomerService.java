package com.example.kntshop.customers.service;

import com.example.kntshop.customers.model.Customer;
import com.example.kntshop.customers.model.CustomerDto;

public interface ICustomerService {
    CustomerDto customer(String accountId);
    void addCustomer(Customer customer);
}
