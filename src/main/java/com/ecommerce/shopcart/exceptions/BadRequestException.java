package com.ecommerce.shopcart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseRuntimeException {
    public BadRequestException(String message, String param, String... messageParamArray) {
        super(message, param, messageParamArray);
    }
}
