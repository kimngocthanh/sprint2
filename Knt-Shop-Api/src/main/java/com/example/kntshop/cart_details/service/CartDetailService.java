package com.example.kntshop.cart_details.service;

import com.example.kntshop.account_users.model.AccountUser;
import com.example.kntshop.account_users.repository.IAccountUserRepository;
import com.example.kntshop.cart_details.dto.CartDetailDto;
import com.example.kntshop.cart_details.dto.CartDetails;
import com.example.kntshop.cart_details.dto.CartDto;
import com.example.kntshop.cart_details.model.CartDetail;
import com.example.kntshop.cart_details.reposiroty.ICartDetailRepository;
import com.example.kntshop.orders.model.Orders;
import com.example.kntshop.orders.repository.OrdersRepository;
import com.example.kntshop.products.model.Product;
import com.example.kntshop.products.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CartDetailService implements ICartDetailService{
    @Autowired
    private ICartDetailRepository cartDetailRepository;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IAccountUserRepository accountUserRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public List<CartDetailDto> cartDetailDto(String userName) {
        return cartDetailRepository.findAllCartDetailDto(userName);
    }

    @Override
    public Boolean checkQuantity(Integer productId, Integer quantity) {
        Product product = productRepository.findById(productId).get();
        if(product.getQuantity()-quantity < 0){
            return false;
        }return true;
    }

    @Override
    public void addCartDetail(String username, Integer productId, Integer quantity) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(username);
        cartDetailRepository.addToCard(accountUser.getId(),productId,quantity);
    }

    @Override
    public void updateCartDetail(Integer quantity, String accountId, Integer idProduct) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(accountId);
        cartDetailRepository.updateCartDetail(quantity,accountUser.getId(),idProduct);
    }

    @Override
    public void deleteOneQuantity(Integer quantity, String accountId, Integer idProduct) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(accountId);
        cartDetailRepository.deleteOneQuantityInCart(quantity,accountUser.getId(),idProduct);
    }

    @Override
    public void deleteCartDetail(String accountId, Integer idProduct) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(accountId);
        cartDetailRepository.deleteCartDetail(accountUser.getId(),idProduct);
    }

    @Override
    public Boolean checkProductInCart(Integer id,String accountId) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(accountId);
        CartDetail cartDetail = cartDetailRepository.cartDetail(id,accountUser.getId());
        if(cartDetail == null){
            return true;
        }
        return false;
    }

    @Override
    public CartDetail cartDetail(Integer idProduct, String accountId) {
        AccountUser accountUser = accountUserRepository.getAccountByUserName(accountId);
        return cartDetailRepository.cartDetail(idProduct,accountUser.getId());
    }

    @Override
    public void addCartDetail(CartDetails cartDetails, String username) {
        List<CartDetailDto> cartDetailDto = cartDetailRepository.findAllCartDetailDto(username);
        AccountUser accountUser = accountUserRepository.getAccountByUserName(username);

        for (CartDetailDto c: cartDetailDto) {
            for (CartDto d: cartDetails.getCartDetailDtoList()) {
                if(Objects.equals(c.getId(), d.getId())){
                    cartDetailRepository.deleteCartDetail(accountUser.getId(),c.getId());
                }
            }
        }
        List<CartDto> cartDetailDtoList = cartDetails.getCartDetailDtoList();
        String code = findMaxCode();
        cartDetailRepository.createOrders(code,LocalDate.now().toString(),accountUser.getId());
        Orders orders = ordersRepository.orders(code);
        for (CartDto c: cartDetailDtoList) {
            cartDetailRepository.updateProduct(c.getQuantity(), c.getId());
            cartDetailRepository.createOrdersDetail(c.getQuantity(),(c.getPrice())*c.getQuantity(),orders.getId(),c.getId());
        }
    }

    @Override
    public String findMaxCode() {
        String maxCode = cartDetailRepository.findMaxCode();
        if (maxCode.equals(""))
            return "KT001"; // Hoặc giá trị mặc định khác cho code đầu tiên
        // Tách phần số từ code lớn nhất hiện tại
        String numericPart = maxCode.substring(2);
        int numericValue = Integer.parseInt(numericPart);
        // Tăng giá trị số lên 1
        numericValue++;
        // Định dạng lại giá trị số thành chuỗi có độ dài 4 và thêm vào tiền tố "HDN"
        String newNumericPart = String.format("%03d", numericValue);
        String newCode = "KT" + newNumericPart;
        return newCode;
    }
}
