package com.ecommerce.shopcart.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDTO {

    private Long id;

    @NotBlank(message = "Category name must not be blank.")
    private String categoryName;
}
