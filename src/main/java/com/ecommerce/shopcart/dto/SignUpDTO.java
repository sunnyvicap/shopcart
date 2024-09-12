package com.ecommerce.shopcart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class SignUpDTO {


    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Please enter valid email.")
    private String email;

    @NotBlank(message = "Password must not be blank.")
    @Size(min = 6, message = "Password must contains at-least 6 characters.")
    private String password;

    @NotBlank(message = "Confirm Password must not be blank.")
    private String confirmPassword;

    @NotNull(message = "Please enter role ids.")
    @Size(min = 1, message = "Please enter role ids.")
    private List<Long> roles;

}
