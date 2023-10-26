package com.example.kntshop.products.model;

import com.example.kntshop.orders_detail.model.OrderDetail;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private String name;
    private String material;
    private Double weight;
    private Integer quantity;
    private Double price;
    private String note;
    private String img;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetailSet;

    public Product() {
    }

    public Product(Integer id, String code, String name, String material, Double weight, Integer quantity, Double price, String note, String img, Set<OrderDetail> orderDetailSet) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.material = material;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
        this.img = img;
        this.orderDetailSet = orderDetailSet;

    }

    public Product(Integer id, String code, String name, String material, Double weight, Integer quantity, Double price, String note, String img) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.material = material;
        this.weight = weight;
        this.quantity = quantity;
        this.price = price;
        this.note = note;
        this.img = img;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<OrderDetail> getOrderDetailSet() {
        return orderDetailSet;
    }

    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
        this.orderDetailSet = orderDetailSet;
    }

}
