package com.example.kntshop.cart_details.dto;

public class CartDto {
    private Integer quantity;
    private String name;
    private String img;
    private Double price;
    private Integer id;

    public CartDto() {
    }

    public CartDto(Integer quantity, String name, String img, Double price, Integer id) {
        this.quantity = quantity;
        this.name = name;
        this.img = img;
        this.price = price;
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
