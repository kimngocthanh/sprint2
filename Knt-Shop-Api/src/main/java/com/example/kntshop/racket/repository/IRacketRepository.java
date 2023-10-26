package com.example.kntshop.racket.repository;

import com.example.kntshop.products.model.ProductRacketDto;
import com.example.kntshop.racket.model.Racket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRacketRepository extends JpaRepository<Racket,Integer> {
    @Query(value = "call find_racket(:id)",nativeQuery = true)
    ProductRacketDto findRacketById(Integer id);


}
