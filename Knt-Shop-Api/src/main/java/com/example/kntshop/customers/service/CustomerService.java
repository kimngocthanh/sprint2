package com.example.kntshop.customers.service;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.account_users.repository.IAccountUserRepository;
import com.example.kntshop.customers.model.Customer;
import com.example.kntshop.customers.model.CustomerDto;
import com.example.kntshop.customers.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @Override
    public CustomerDto customer(String account) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(account);
        return customerRepository.getCustomer(accountUser.getId()) ;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
