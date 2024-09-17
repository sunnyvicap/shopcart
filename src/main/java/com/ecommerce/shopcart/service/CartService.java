package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.CartDTO;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import com.ecommerce.shopcart.model.Cart;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.CartRepository;
import com.ecommerce.shopcart.repository.ProductRepository;
import com.ecommerce.shopcart.security.UserDetailServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private ModelMapper mapper;

    public CartDTO addProductToCart(Long productId , Integer quantity){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found for product id " , "productId : " + productId));

        Cart cart = createCart();


        return mapper.map(cart , CartDTO.class);
    }

    private Cart createCart() {
        User user = userDetailService.getLoggedInUser();
        Cart userCart = cartRepository.findCartByUserId(user.getId());

        if(userCart != null){
            return userCart;
        }
        Cart cart = new Cart();
        cart.setTotalPrice(0.0);
        cart.setUser(user);

        return cartRepository.save(cart);
    }
}
