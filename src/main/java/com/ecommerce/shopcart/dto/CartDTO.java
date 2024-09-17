package com.ecommerce.shopcart.dto;

import com.ecommerce.shopcart.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    private Long cartId;

    private Double totalPrice = 0.0;

    private List<Product> products;
}
