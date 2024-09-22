package com.ebay.assignment.calculator.exception;

public class CalculatorArithmeticException extends RuntimeException {

    public CalculatorArithmeticException(String message) {
        super(message);
    }

    public CalculatorArithmeticException(String message, Throwable cause) {
        super(message, cause);
    }
}
