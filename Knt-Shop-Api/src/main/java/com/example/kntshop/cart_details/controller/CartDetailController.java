package com.example.kntshop.cart_details.controller;

import com.example.kntshop.cart_details.dto.CartDetailDto;
import com.example.kntshop.cart_details.dto.CartDetails;
import com.example.kntshop.cart_details.model.CartDetail;
import com.example.kntshop.cart_details.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin("*")
public class CartDetailController {
    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping("/cartDetail")
    public ResponseEntity<?> findAll(@RequestParam String username){
        List<CartDetailDto> cartDetailDtoList = cartDetailService.cartDetailDto(username);
        return new ResponseEntity<>(cartDetailDtoList, HttpStatus.OK);
    }

    @GetMapping("/check-quantity")
    public ResponseEntity<?> checkQuantity(@RequestParam Integer productId, @RequestParam Integer quantity){
        if(cartDetailService.checkQuantity(productId,quantity)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-cart")
    public ResponseEntity<?> addToCart(@RequestParam("username") String username, @RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity ) {
        cartDetailService.addCartDetail(username,productId,quantity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/update-cart")
    public ResponseEntity<?> updateToCart(@RequestParam("username") String username, @RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity ) {
        cartDetailService.updateCartDetail(quantity,username,productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/delete-one-quantity")
    public ResponseEntity<?> deleteOneQuantity(@RequestParam("username") String username, @RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity ) {
        cartDetailService.deleteOneQuantity(quantity, username, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-cart")
    public ResponseEntity<?> deleteCartDetail(@RequestParam("username") String username, @RequestParam("productId") Integer productId){
        cartDetailService.deleteCartDetail(username,productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check-product-cart")
    public ResponseEntity<?> checkProductCart(@RequestParam("productId") Integer id,@RequestParam("username") String username){
        if(Boolean.TRUE.equals(cartDetailService.checkProductInCart(id,username))){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/add-to-cart")
    public ResponseEntity<?> addToCartInDetail(@RequestParam("username") String username, @RequestParam("productId") Integer productId,@RequestParam("quantity") Integer quantity ) {
        if(Boolean.TRUE.equals(cartDetailService.checkProductInCart(productId,username))){
            cartDetailService.addCartDetail(username,productId,quantity);
            if(Boolean.TRUE.equals(cartDetailService.checkQuantity(productId,quantity))){
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            cartDetailService.deleteCartDetail(username,productId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        cartDetailService.updateCartDetail(quantity,username,productId);

        CartDetail cartDetail = cartDetailService.cartDetail(productId,username);
        if(Boolean.TRUE.equals(cartDetailService.checkQuantity(productId,cartDetail.getQuantity()+quantity ))){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        cartDetailService.deleteOneQuantity(quantity,username,productId);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/add-to-order")
    public ResponseEntity<?> addToOrder(@RequestBody CartDetails cartDetails,@RequestParam("username") String username) {
        System.out.println(cartDetails);
        cartDetailService.addCartDetail(cartDetails,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
