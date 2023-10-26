package com.example.kntshop.orders.model;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.orders_detail.model.OrderDetail;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String dateTime;

    private Boolean flagDeleted;

    private String note;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountUser accountUser;

//    @JsonBackReference
//    @JsonIgnore
//    @OneToMany(mappedBy = "orders")
//    private Set<OrderDetail> orderDetailSet;

    public Orders() {
    }

    public Orders(Long id, String code, String dateTime, Boolean flagDeleted, String note, AccountUser accountUser) {
        this.id = id;
        this.code = code;
        this.dateTime = dateTime;
        this.flagDeleted = flagDeleted;
        this.note = note;
        this.accountUser = accountUser;
//        this.orderDetailSet = orderDetailSet;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Boolean getFlagDeleted() {
        return flagDeleted;
    }

    public void setFlagDeleted(Boolean flagDeleted) {
        this.flagDeleted = flagDeleted;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }

//    public Set<OrderDetail> getOrderDetailSet() {
//        return orderDetailSet;
//    }
//
//    public void setOrderDetailSet(Set<OrderDetail> orderDetailSet) {
//        this.orderDetailSet = orderDetailSet;
//    }
}
