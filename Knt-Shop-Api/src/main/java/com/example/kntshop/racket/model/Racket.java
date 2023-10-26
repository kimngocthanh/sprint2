package com.example.kntshop.racket.model;

import com.example.kntshop.products.model.Product;

import javax.persistence.*;

@Entity
public class Racket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String racketType;
    private String handleSize;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public Racket() {
    }

    public Racket(Integer id, String racketType, String handleSize, Product product) {
        this.id = id;
        this.racketType = racketType;
        this.handleSize = handleSize;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Racket(Integer id, String racketType, String handleSize) {
        this.id = id;
        this.racketType = racketType;
        this.handleSize = handleSize;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRacketType() {
        return racketType;
    }

    public void setRacketType(String racketType) {
        this.racketType = racketType;
    }

    public String getHandleSize() {
        return handleSize;
    }

    public void setHandleSize(String handleSize) {
        this.handleSize = handleSize;
    }
}
