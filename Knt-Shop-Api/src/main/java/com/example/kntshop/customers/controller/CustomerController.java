package com.example.kntshop.customers.controller;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.account_users.repository.IAccountUserRepository;
import com.example.kntshop.customers.model.Customer;
import com.example.kntshop.customers.model.CustomerDto;
import com.example.kntshop.customers.model.CustomerDtos;
import com.example.kntshop.customers.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @GetMapping("/customer/detail")
    public ResponseEntity<?> getCustomer(@RequestParam String username){
        CustomerDto customerDto = customerService.customer(username);
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }
    @PostMapping("/customer/add")
    public ResponseEntity<?> createCustomer(@RequestBody CustomerDtos customerDtos,@RequestParam String username){
        Customer customer = new Customer();
        customer.setId(customerDtos.getId());
        customer.setAddress(customerDtos.getAddress());
        customer.setDob(customerDtos.getDob());
        customer.setName(customerDtos.getName());
        customer.setPhone(customerDtos.getPhone());
        AccountUser accountUser = accountUserRepository.getAccountByUserName(username);
        customer.setAccountUser(accountUser);
        customer.setFlagDeleted(false);
        customer.setPoint(0);
        customerService.addCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
