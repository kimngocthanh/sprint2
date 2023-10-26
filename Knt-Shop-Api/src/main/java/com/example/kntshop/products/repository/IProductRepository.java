package com.example.kntshop.products.repository;

import com.example.kntshop.products.model.Product;
import com.example.kntshop.products.model.ProductBirdieDto;
import com.example.kntshop.products.model.ProductRacketDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface IProductRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query(value = "Call create_racket(:productRacketDto.code, :productRacketDto.img, :productRacketDto.material, :productRacketDto.name," +
            ":productRacketDto.note, :productRacketDto.price,:productRaketDto.quantity,:productRacketDto.weight," +
            ":productRacketDto.handleSize, :productRacketDto.racketType) ",nativeQuery = true)
    void createRacket(@Param("productRacketDto") ProductRacketDto productRacketDto);


    @Modifying
    @Transactional
    @Query(value = "Call create_birdie(:productBirdieDto.code, :productBirdieDto.img, :productBirdieDto.material, :productBirdieDto.name, " +
            " :productBirdieDto.note, :productBirdieDto.price,:productBirdieDto.quantity,:productBirdieDto.weight,:productBirdieDto.bridgeSpeed)",nativeQuery = true)
    void createBirdie(@Param("productBirdieDto") ProductBirdieDto productBirdieDto);

    @Query(value = "select * from product p where p.name like concat('%', :name, '%') ",nativeQuery = true)
    Page<Product> getProductByName(String name, Pageable pageable);

    @Query(value = "select  p.id,p.code,p.name,p.price,p.img " +
            "from product p " +
            "join birdie b on p.id = b.product_id ",nativeQuery = true)
    Page<ProductBirdieDto> getAllProductBirdie(Pageable pageable);

    @Query(value = "select  p.id,p.code,p.name,p.price,p.img " +
            "from product p " +
            "join racket r on p.id = r.product_id ",nativeQuery = true)
    Page<ProductRacketDto> getAllProductRacket(Pageable pageable);
}
