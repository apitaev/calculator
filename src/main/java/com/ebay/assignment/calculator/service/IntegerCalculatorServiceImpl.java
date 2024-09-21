package com.ebay.assignment.calculator.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegerCalculatorServiceImpl implements CalculatorService<Integer> {
    @Override
    public Integer calculate(Operation operation, Integer number1, Integer number2) throws IllegalArgumentException {
        if (operation == null) throw new IllegalArgumentException(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION);
        if (number1 == null || number2 == null)
            throw new IllegalArgumentException(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION);
        return switch (operation) {
            case ADD -> Math.addExact(number1, number2);
            case SUBTRACT -> number1 - number2;
            case MULTIPLY -> Math.multiplyExact(number1, number2);
            case DIVIDE -> number1 / number2;
            default -> throw new UnsupportedOperationException(Constants.OPERATION_NOT_SUPPORTED_EXCEPTION);
        };
    }

    @Override
    public Integer calculateChain(Integer initialValue, List<Operation> operations, List<Integer> numbers) throws IllegalArgumentException {
        if (operations == null) throw new IllegalArgumentException(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION);
        if (initialValue == null || numbers == null)
            throw new IllegalArgumentException(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION);
        if (operations.size() != numbers.size())
            throw new IllegalArgumentException(Constants.NUMBER_OF_CHAIN_OPERATIONS_NOT_EQUAL_NUMBER_OF_CHAIN_ARGUMENTS_EXCEPTION);
        Integer result = initialValue;
        // check size of operation list and the second argument lists
        for (int i = 0; i < operations.size(); i++) {
            result = calculate(operations.get(i), result, numbers.get(i));
        }
        return result;
    }
}
