package com.ebay.assignment.calculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CalculatorExceptionHandler {
    @ExceptionHandler(value = CalculatorNotSupportedOperationException.class)
    public ResponseEntity<Object> handleCalculatorNotSupportedOperationException(CalculatorNotSupportedOperationException exception) {
        CalculatorException calculatorException = new CalculatorException(exception.getMessage(), exception.getCause(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(calculatorException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CalculatorIllegalArgumentException.class)
    public ResponseEntity<Object> handleCalculatorIllegalArgumentException(CalculatorIllegalArgumentException exception) {
        CalculatorException calculatorException = new CalculatorException(exception.getMessage(), exception.getCause(),
                HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(calculatorException, HttpStatus.BAD_REQUEST);
    }
}
