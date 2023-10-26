package com.example.kntshop.orders_detail.repository;

import com.example.kntshop.orders_detail.model.IOrderDetailDto;
import com.example.kntshop.orders_detail.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
    @Query(value = "select p.name,p.img,od.total_price as totalPrice ,od.quantity " +
            "from order_detail od " +
            "join orders o on od.orders_id = o.id " +
            "join product p on od.product_id = p.id " +
            "where o.account_id = :idUsername and o.code like :code",nativeQuery = true)
    List<IOrderDetailDto> getAllOrderDetail(Integer idUsername, String code);
}
