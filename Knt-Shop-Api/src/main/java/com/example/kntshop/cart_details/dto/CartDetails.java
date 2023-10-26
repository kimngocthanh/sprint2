package com.example.kntshop.cart_details.dto;

import java.util.List;

public class CartDetails {
    private List<CartDto> cartDetailDtoList;

    public CartDetails() {
    }

    public CartDetails(List<CartDto> cartDetailDtoList) {
        this.cartDetailDtoList = cartDetailDtoList;
    }

    public List<CartDto> getCartDetailDtoList() {
        return cartDetailDtoList;
    }

    public void setCartDetailDtoList(List<CartDto> cartDetailDtoList) {
        this.cartDetailDtoList = cartDetailDtoList;
    }
}
