package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {
    @Query(value = "SELECT c FROM ShoppingCart c WHERE c.user.id = :userId")
    List<ShoppingCart> findAllByUserId(Long userId);
}
