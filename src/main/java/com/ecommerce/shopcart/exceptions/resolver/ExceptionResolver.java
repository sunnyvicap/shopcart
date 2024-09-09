package com.ecommerce.shopcart.exceptions.resolver;

import com.ecommerce.shopcart.exceptions.BadRequestException;
import com.ecommerce.shopcart.exceptions.BaseRuntimeException;
import com.ecommerce.shopcart.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.sql.Timestamp;

@RestControllerAdvice
public class ExceptionResolver {
    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionResolver.class);

    @Value("${application.name}")
    private String serviceName;


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> myMethodArgumentNotValid(MethodArgumentNotValidException e, WebRequest request) {
        ExceptionDetails exceptionDetails = getExceptionDetail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getLocalizedMessage(), "", request);
        e.getBindingResult().getAllErrors().forEach(err -> {
            exceptionDetails.setMessage(err.getDefaultMessage());
            exceptionDetails.setDetailMessage(((FieldError) err).getField());
        });
        return ResponseEntity.badRequest().body(exceptionDetails);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ExceptionDetails> myNoResourceFound(NoResourceFoundException e, WebRequest request) {
        ExceptionDetails exceptionDetails = getExceptionDetail(HttpStatus.NOT_FOUND.value(), e.getMessage(), e.getLocalizedMessage(), "", request);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<ExceptionDetails> myBadRequestException(BaseRuntimeException e, WebRequest request) {
        ExceptionDetails exceptionDetails = getExceptionDetail(HttpStatus.BAD_REQUEST.value(), e.getMessage(), e.getLocalizedMessage(), e.getParam(), request);
        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }

    private ExceptionDetails getExceptionDetail(int statusCode, String message, String localizedMessage, String param, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(new Timestamp(System.currentTimeMillis()));
        exceptionDetails.setStatusCode(statusCode);
        exceptionDetails.setBasePath(request.getContextPath());
        exceptionDetails.setDetails(request.getDescription(false));
        exceptionDetails.setServiceName(serviceName);
        exceptionDetails.setMessage(message);
        exceptionDetails.setDetailMessage(localizedMessage);
        exceptionDetails.setParam(param);
        return exceptionDetails;
    }


}
