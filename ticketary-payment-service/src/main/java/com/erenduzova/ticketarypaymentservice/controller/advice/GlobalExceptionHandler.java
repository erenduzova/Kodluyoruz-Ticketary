package com.erenduzova.ticketarypaymentservice.controller.advice;

import com.erenduzova.ticketarypaymentservice.exception.ExceptionResponse;
import com.erenduzova.ticketarypaymentservice.exception.TicketaryPaymentServiceException;
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

    @ExceptionHandler(TicketaryPaymentServiceException.class)
    public ResponseEntity<ExceptionResponse> handle(TicketaryPaymentServiceException exception){
        return ResponseEntity.ok(new ExceptionResponse(exception.getMessage(), HttpStatus.BAD_REQUEST));
    }

}
