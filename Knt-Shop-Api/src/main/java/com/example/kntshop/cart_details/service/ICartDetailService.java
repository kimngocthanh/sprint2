package com.example.kntshop.cart_details.service;

import com.example.kntshop.cart_details.dto.CartDetailDto;
import com.example.kntshop.cart_details.dto.CartDetails;
import com.example.kntshop.cart_details.model.CartDetail;

import java.util.List;

public interface ICartDetailService {
    List<CartDetailDto> cartDetailDto(String userName);
    Boolean checkQuantity(Integer productId, Integer quantity);
    void addCartDetail(String username,Integer productId, Integer quantity);
    void updateCartDetail(Integer quantity,String accountId, Integer idProduct);
    void deleteOneQuantity(Integer quantity,String accountId, Integer idProduct);
    void deleteCartDetail(String accountId, Integer idProduct);
    Boolean checkProductInCart(Integer id,String accountId);
    CartDetail cartDetail(Integer idProduct,String accountId);
    void addCartDetail(CartDetails cartDetails, String username);
    String findMaxCode();
//    void createOrder(String code, String date , Integer accountId);
}
