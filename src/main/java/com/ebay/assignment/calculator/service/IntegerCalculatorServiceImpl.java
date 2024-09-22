package com.ebay.assignment.calculator.service;

import com.ebay.assignment.calculator.exception.CalculatorArithmeticException;
import com.ebay.assignment.calculator.exception.CalculatorIllegalArgumentException;
import com.ebay.assignment.calculator.exception.CalculatorNotSupportedOperationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;

@Service
public class IntegerCalculatorServiceImpl implements CalculatorService<Integer> {
    static Consumer<Integer> lambdaWrapper(Consumer<Integer> consumer) {
        return i -> {
            try {
                consumer.accept(i);
            } catch (ArithmeticException e) {
                throw new CalculatorArithmeticException(e.getMessage());
            }
        };
    }

    @Override
    public Integer calculate(Operation operation, Integer number1, Integer number2) {
        if (operation == null)
            throw new CalculatorIllegalArgumentException(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION);
        if (number1 == null || number2 == null)
            throw new CalculatorIllegalArgumentException(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION);
        try {
            return switch (operation) {
                case ADD -> Math.addExact(number1, number2);
                case SUBTRACT -> number1 - number2;
                case MULTIPLY -> Math.multiplyExact(number1, number2);
                case DIVIDE -> number1 / number2;
                default ->
                        throw new CalculatorNotSupportedOperationException(Constants.OPERATION_NOT_SUPPORTED_EXCEPTION);
            };
        } catch (ArithmeticException e) {
            throw new CalculatorArithmeticException(e.getMessage());
        }
    }

    @Override
    public Integer calculateChain(Integer initialValue, List<Operation> operations, List<Integer> numbers) {
        if (operations == null)
            throw new CalculatorIllegalArgumentException(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION);
        if (initialValue == null || numbers == null)
            throw new CalculatorIllegalArgumentException(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION);
        if (operations.size() != numbers.size())
            throw new CalculatorIllegalArgumentException(Constants.NUMBER_OF_CHAIN_OPERATIONS_NOT_EQUAL_NUMBER_OF_CHAIN_ARGUMENTS_EXCEPTION);
        Integer result = initialValue;
        // check size of operation list and the second argument lists
        for (int i = 0; i < operations.size(); i++) {
            result = calculate(operations.get(i), result, numbers.get(i));
        }
        return result;
    }
}
