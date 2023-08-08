package com.jangsuhyeon.shop.repository;

import com.jangsuhyeon.shop.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByRegDtDesc(Pageable pageable);

    Page<Product> findAllByCateIdOrderByRegDtDesc(Long cateId, Pageable pageable);

    Page<Product> findByCateIdAndBrdIdIn(Long cateId, Long[] brdId, Pageable pageable);

    @Query("SELECT MAX(p.prtPrice) FROM Product p")
    int findMaxPrtPrice();

    @Query("SELECT MIN(p.prtPrice) FROM Product p")
    int findMinPrtPrice();
}
