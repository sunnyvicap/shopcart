package com.ecommerce.shopcart.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;

@Setter
@Getter
public class BaseRuntimeException extends RuntimeException {

    private String param;

    public BaseRuntimeException(String message, String param, String... messageParamArray) {
        super((messageParamArray != null && messageParamArray.length > 0)
                ?
                MessageFormat.format(
                        StringUtils.replace(message, "'", "''"),
                        (Object[]) messageParamArray)
                :
                message
        );
        this.param = param;
    }
}
