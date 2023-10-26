package com.example.kntshop.customers.repository;

import com.example.kntshop.customers.model.Customer;
import com.example.kntshop.customers.model.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    @Query(value = "select c.id,c.phone,c.address,c.dob,c.name from customer c where c.account_id = :id ",nativeQuery = true)
    CustomerDto getCustomer(Integer id);
}
