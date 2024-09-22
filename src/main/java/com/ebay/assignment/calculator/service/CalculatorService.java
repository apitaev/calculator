package com.ebay.assignment.calculator.service;

import java.util.List;

public interface CalculatorService<T extends Number> {

    T calculate(Operation operation, T number1, T number2);

    T calculateChain(T initialValue, List<Operation> operations, List<T> numbers);
}
