package com.ecommerce.shopcart.util;


public class Utility {

    public static double calculateDiscountPrice(double price , double discount){
        double specialPrice = (price * discount) / 100;
        specialPrice = price - specialPrice;
        return specialPrice;
    }

}
