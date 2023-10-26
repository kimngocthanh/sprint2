package com.example.kntshop.birdie.model;

import com.example.kntshop.products.model.Product;

import javax.persistence.*;

@Entity
public class Birdie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String bridgeSpeed;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    Product product;

    public Birdie() {
    }

    public Birdie(Integer id, String bridgeSpeed, Product product) {
        this.id = id;
        this.bridgeSpeed = bridgeSpeed;
        this.product = product;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBridgeSpeed() {
        return bridgeSpeed;
    }

    public void setBridgeSpeed(String bridgeSpeed) {
        this.bridgeSpeed = bridgeSpeed;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
