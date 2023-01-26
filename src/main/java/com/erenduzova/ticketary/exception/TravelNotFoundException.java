package com.erenduzova.ticketary.exception;

public class TravelNotFoundException extends RuntimeException{
    public TravelNotFoundException(String message) {
        super(message);
    }
}
