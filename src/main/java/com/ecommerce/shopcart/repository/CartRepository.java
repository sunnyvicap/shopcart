package com.ecommerce.shopcart.repository;

import com.ecommerce.shopcart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart , Long> {

    Cart findCartByUserId(Long userId);
}
