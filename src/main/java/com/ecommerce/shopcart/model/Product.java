package com.ecommerce.shopcart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String description;

    private String image;

    private Integer Quantity;

    private double price;

    private double specialPrice;

    private double discount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


}
