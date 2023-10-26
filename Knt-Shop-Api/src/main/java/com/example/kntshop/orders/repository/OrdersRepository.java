package com.example.kntshop.orders.repository;

import com.example.kntshop.orders.model.IOrdersDto;
import com.example.kntshop.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long> {
    @Query(value = "select * from orders o where o.code like :code",nativeQuery = true)
    Orders orders(String code);

    @Query(value = "select o.code,o.date_time as dateTime, sum(od.total_price) as totalPrice from orders o " +
            "join account_user ac on o.account_id = ac.id " +
            "join order_detail od on o.id=od.orders_id " +
            "where ac.id = :id " +
            "group by o.code,o.date_time " ,nativeQuery = true)
    List<IOrdersDto> getAllOrders(Integer id);

}
