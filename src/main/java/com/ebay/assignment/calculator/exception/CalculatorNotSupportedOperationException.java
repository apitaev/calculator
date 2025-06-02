package com.ebay.assignment.calculator.exception;

public class CalculatorNotSupportedOperationException extends RuntimeException {

    public CalculatorNotSupportedOperationException(String message) {
        super(message);
    }

    public CalculatorNotSupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
