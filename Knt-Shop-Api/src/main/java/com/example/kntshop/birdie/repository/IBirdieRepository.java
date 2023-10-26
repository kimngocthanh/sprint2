package com.example.kntshop.birdie.repository;

import com.example.kntshop.birdie.model.Birdie;
import com.example.kntshop.products.model.ProductBirdieDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IBirdieRepository extends JpaRepository<Birdie,Integer> {
    @Query(value = "call find_birdie(:id)",nativeQuery = true)
    ProductBirdieDto findBirdieById(Integer id);
}
