package com.onlineshop.products.catalogue.exception;

import com.onlineshop.products.catalogue.constants.CatalogueConstants;
import com.onlineshop.products.catalogue.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> categoryDoesNotExistException(ProductNotFoundException rse,
                                                                  WebRequest webRequest){
        log.error("Exception occurred (categoryDoesNotExistException): " + rse.getMessage());
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now().toString(), rse.getMessage(), webRequest.getDescription(false), CatalogueConstants.ERROR_CODE_CATEGORY_NOT_EXIST);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleResourceNotFoundException(Exception exception,
                                                                        WebRequest webRequest){
        log.error("Exception occurred (handleResourceNotFoundException) : " + exception.getMessage());
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now().toString(), exception.getMessage(), webRequest.getDescription(false), CatalogueConstants.ERROR_CODE_INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    /*@Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now().toString(), ex.getMessage(), webRequest.getDescription(false), CatalogueConstants.ERROR_CODE_CATEGORY_MISSING_IN_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        Map<String, String> errorMessages = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            errorMessages.put(((FieldError) error).getField(), error.getDefaultMessage());
        });
        ErrorDto errorDetails = new ErrorDto(LocalDateTime.now().toString(), errorMessages.toString(), webRequest.getDescription(false), CatalogueConstants.ERROR_CODE_CATEGORY_MISSING_IN_REQUEST);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }*/
}
