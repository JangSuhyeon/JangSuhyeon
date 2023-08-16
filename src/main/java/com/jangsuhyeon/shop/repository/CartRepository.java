package com.jangsuhyeon.shop.repository;

import com.jangsuhyeon.shop.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByPrtId(Long prtId);
}
