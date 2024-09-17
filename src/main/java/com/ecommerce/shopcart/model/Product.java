package com.ecommerce.shopcart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User user;

    @OneToMany(mappedBy = "product" , cascade = {CascadeType.PERSIST, CascadeType.MERGE},
     fetch = FetchType.EAGER)
    private List<CartItem> cartItems;
}
