package com.example.kntshop.cart_details.reposiroty;

import com.example.kntshop.cart_details.dto.CartDetailDto;
import com.example.kntshop.cart_details.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface ICartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Query(value = "call cartDetail(:userName)", nativeQuery = true)
    List<CartDetailDto> findAllCartDetailDto(String userName);

    @Transactional
    @Modifying
    @Query(value = "insert into cart_detail(account_id,product_id,quantity) " +
            "values(:idUsername, :idProduct, :quantity)", nativeQuery = true)
    void addToCard(Integer idUsername, Integer idProduct, Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "update cart_detail cd set quantity = cd.quantity + :quantity where cd.account_id = :accountId and cd.product_id = :idProduct", nativeQuery = true)
    void updateCartDetail(Integer quantity, Integer accountId, Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = "update cart_detail cd set quantity = cd.quantity - :quantity where cd.account_id = :accountId and cd.product_id = :idProduct", nativeQuery = true)
    void deleteOneQuantityInCart(Integer quantity, Integer accountId, Integer idProduct);

    @Transactional
    @Modifying
    @Query(value = "delete from cart_detail cd where cd.product_id= :idProduct and cd.account_id= :accountId", nativeQuery = true)
    void deleteCartDetail(Integer accountId, Integer idProduct);

    @Transactional
    @Query(value = "select * from cart_detail cd where cd.product_id = :idProduct and cd.account_id = :accountId", nativeQuery = true)
    CartDetail cartDetail(Integer idProduct, Integer accountId);

    @Query(value = "call addOrders(:code,:date,:accountId,:quantity,:totalPrice,:idProduct)", nativeQuery = true)
    void createOrder(String code, String date, Integer accountId, Integer quantity, Double totalPrice, Integer idProduct);

    @Query(value = "SELECT MAX(code) FROM orders where flag_deleted = 0", nativeQuery = true)
    String findMaxCode();

    @Transactional
    @Modifying
    @Query(value = "insert into orders(code,date_time,flag_deleted,note,account_id) " +
            "values(:code,:date, 0,' ' , :accountId) ",nativeQuery = true)
    void createOrders(String code, String date , Integer accountId );

    @Transactional
    @Modifying
    @Query(value = "insert into order_detail(quantity , total_price , orders_id, product_id) " +
            "values(:quantity,:totalPrice,:ordersId,:productId) ",nativeQuery = true)
    void createOrdersDetail(Integer quantity, Double totalPrice, Long ordersId, Integer productId);

    @Transactional
    @Modifying
    @Query(value = "update product p set quantity = p.quantity- :quantity where p.id = :idProduct ",nativeQuery = true)
    void updateProduct(Integer quantity, Integer idProduct);
}
