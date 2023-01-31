package com.erenduzova.ticketary.controller.advice;

import com.erenduzova.ticketary.exception.ExceptionResponse;
import com.erenduzova.ticketary.exception.PaymentFailedException;
import com.erenduzova.ticketary.exception.TicketaryServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(TicketaryServiceException.class)
    public ResponseEntity<ExceptionResponse> handle(TicketaryServiceException exception){
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(PaymentFailedException.class)
    public ResponseEntity<ExceptionResponse> handle(PaymentFailedException exception){
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED));
    }

}
