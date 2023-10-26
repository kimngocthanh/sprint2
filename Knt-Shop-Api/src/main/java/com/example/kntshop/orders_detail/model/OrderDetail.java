package com.example.kntshop.orders_detail.model;

import com.example.kntshop.orders.model.Orders;
import com.example.kntshop.products.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double totalPrice;
    private Integer quantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orders_id", referencedColumnName = "id")
    private Orders orders;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(Integer id, Double totalPrice, Integer quantity, Orders orders, Product product) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.orders = orders;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders orders) {
        this.orders = orders;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
