package com.example.kntshop.customers.model;

import com.example.kntshop.account_users.model.AccountUser;


import javax.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private Integer point;
    private String dob;
    private String phone;
    private Boolean flagDeleted;

    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountUser accountUser;



    public Customer() {
    }

    public Customer(Integer id, String name, String address, Integer point, String dob, String phone, Boolean flagDeleted, AccountUser accountUser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.point = point;
        this.dob = dob;
        this.phone = phone;
        this.flagDeleted = flagDeleted;
        this.accountUser = accountUser;

    }

    public Customer(Integer id, String name, String address, Integer point, String dob, String phone, AccountUser accountUser) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.point = point;
        this.dob = dob;
        this.phone = phone;
        this.accountUser = accountUser;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

    public Boolean getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }


}
