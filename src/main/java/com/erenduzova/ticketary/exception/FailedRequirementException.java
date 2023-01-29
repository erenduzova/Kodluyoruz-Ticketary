package com.erenduzova.ticketary.exception;

public class FailedRequirementException extends RuntimeException{
    public FailedRequirementException(String message) {
        super(message);
    }
}
