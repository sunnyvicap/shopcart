package com.ecommerce.shopcart.model;

import lombok.Getter;

@Getter
public enum AppRole {
    ROLE_USER("Permission for view and purchase product."),
    ROLE_SELLER("Permission for view and sell product."),
    ROLE_ADMIN("Permission for view,create and edit product.");

    private final String descriptions;

    AppRole(String descriptions) {
        this.descriptions = descriptions;
    }

}
