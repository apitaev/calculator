package com.ebay.assignment.calculator.service;

import com.ebay.assignment.calculator.exception.CalculatorNotSupportedOperationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoubleCalculatorServiceImpl implements CalculatorService<Double> {
    @Override
    public Double calculate(Operation operation, Double number1, Double number2) {
        return switch (operation) {
            case ADD -> number1 + number2;
            case SUBTRACT -> number1 - number2;
            case MULTIPLY -> number1 * number2;
            case DIVIDE -> number1 / number2;
            default -> throw new CalculatorNotSupportedOperationException(Constants.OPERATION_NOT_SUPPORTED_EXCEPTION);
        };
    }

    @Override
    public Double calculateChain(Double initialValue, List<Operation> operations, List<Double> numbers) {
        Double result = initialValue;
        // check size of
        for (int i = 0; i < operations.size(); i++) {
            result = calculate(operations.get(i), result, numbers.get(i));
        }
        return result;
    }
}
