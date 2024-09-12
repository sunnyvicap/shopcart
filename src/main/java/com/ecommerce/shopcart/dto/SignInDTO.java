package com.ecommerce.shopcart.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInDTO {

    @NotBlank(message = "Email must not be blank.")
    @Email
    private String email;

    @NotBlank(message = "Password must not be blank.")
    @Size(min = 6 , message = "Password must contains at-least 6 characters.")
    private String password;
}
