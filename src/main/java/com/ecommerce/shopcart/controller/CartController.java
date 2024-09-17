package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.CartDTO;
import com.ecommerce.shopcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("carts/products/{product-id}/quantity/{quantity}")
    private ResponseEntity<CartDTO> addProductToCard(@PathVariable(value = "product-id") Long productId,
                                                     @PathVariable Integer quantity) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(cartService.addProductToCart(productId, quantity));
    }
}
