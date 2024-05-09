package com.ariamath.shopsmart.repository;

import com.ariamath.shopsmart.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{

    RefreshToken findByUserId(Long userId);

}