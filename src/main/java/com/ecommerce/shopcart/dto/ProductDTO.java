package com.ecommerce.shopcart.dto;

import com.ecommerce.shopcart.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private int id;

    @NotBlank(message = "Product name must not be blank.")
    private String productName;

    private String description;

    private String image;

    private Integer quantity;

    private double price;

    private double specialPrice;

    private double discount;

    private int categoryId;


}
