package com.jangsuhyeon.shop.repository;

import com.jangsuhyeon.shop.domain.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderByRegDtDesc(Pageable pageable);
}
