package com.example.kntshop.customers.model;

public class CustomerDtos {
    private String name;
    private String address;
    private Integer id;
    private String dob;
    private String phone;


    public CustomerDtos() {
    }

    public CustomerDtos(String name, String address, Integer id, String dob, String phone) {
        this.name = name;
        this.address = address;
        this.id = id;
        this.dob = dob;
        this.phone = phone;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
