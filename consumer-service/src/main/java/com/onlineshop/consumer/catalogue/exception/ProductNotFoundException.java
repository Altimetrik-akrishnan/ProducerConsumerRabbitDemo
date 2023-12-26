package com.onlineshop.consumer.catalogue.exception;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String source, String message, String code){
        super(String.format("%s  %s : '%s'", source, message, code));
    }
}
