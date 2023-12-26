package com.onlineshop.products.catalogue.exception;

public class NoSufficientProductsException extends RuntimeException {
    public NoSufficientProductsException(String source, String message, String code){
        super(String.format("%s  %s : '%s'", source, message, code));
    }
}
