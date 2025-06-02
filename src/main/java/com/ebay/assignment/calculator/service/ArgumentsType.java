package com.ebay.assignment.calculator.service;


public enum ArgumentsType {
    INTEGER("integer"),
    DOUBLE("double");

    private final String numericType;

    ArgumentsType(String numericType) {
        this.numericType = numericType.toLowerCase();
    }
}
