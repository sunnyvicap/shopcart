package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.SignInDTO;
import com.ecommerce.shopcart.dto.SignUpDTO;
import com.ecommerce.shopcart.dto.UserDTO;
import com.ecommerce.shopcart.security.jwt.JwtUtils;
import com.ecommerce.shopcart.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth/sign-in")
    public ResponseEntity<UserDTO> signInUser(@Valid @RequestBody SignInDTO signIn) {

        return authService.signInUser(signIn);
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<UserDTO> signInUser(@Valid @RequestBody SignUpDTO signUp) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUpUser(signUp));
    }


    @GetMapping("/user")
    public ResponseEntity<UserDTO> getAuthenticatedUser(Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.getUserDetails(authentication));
    }

    @PostMapping("/sign-out")
    public ResponseEntity<String> signOutUser(Authentication authentication){
        return authService.signOutUser();
    }
}
