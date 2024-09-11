package com.ecommerce.shopcart.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address1;

    private String address2;

    private String state;

    private String city;

    private String pinCode;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;

}
