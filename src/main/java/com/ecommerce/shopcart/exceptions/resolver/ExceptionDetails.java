package com.ecommerce.shopcart.exceptions.resolver;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ExceptionDetails {

    private Timestamp timestamp;

    private int statusCode;

    private String message;

    private String detailMessage;

    private String basePath;

    private String details;

    private String param;

    private String serviceName;
}
