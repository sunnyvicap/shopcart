package com.ecommerce.shopcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends BaseRuntimeException{
    public ResourceNotFoundException(String message, String param, String... messageParamArray) {
        super(message, param, messageParamArray);
    }
}
