package com.ecommerce.shopcart.dto;

import com.ecommerce.shopcart.model.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    @NotBlank(message = "Product name must not be blank.")
    private String productName;

    private String description;

    private String image;

    @Min(value = 1, message = "Quantity must be greater than 1.")
    private Integer quantity;

    @Min(value = 1L, message = "Price must be greater than 1.")
    private double price;

    private double specialPrice;

    private double discount;

}
