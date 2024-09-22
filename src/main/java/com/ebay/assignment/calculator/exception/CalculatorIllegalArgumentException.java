package com.ebay.assignment.calculator.exception;

public class CalculatorIllegalArgumentException extends RuntimeException {

    public CalculatorIllegalArgumentException(String message) {
        super(message);
    }

    public CalculatorIllegalArgumentException(String message, Throwable cause) {
        super(message, cause);
    }
}
