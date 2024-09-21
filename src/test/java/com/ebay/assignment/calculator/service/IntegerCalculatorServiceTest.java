package com.ebay.assignment.calculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@SpringBootTest
public class IntegerCalculatorServiceTest {

    @Autowired
    private CalculatorService<Integer> integerCalculatorServiceImpl;

    @Test()
    public void testCalculateWithNullArguments() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, null, null);
                }, "IllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithFirstArgumentNull() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, null, 5);
                }, "IllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithSecondArgumentNull() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, 5, null);
                }, "IllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithOperationTypeNull() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            integerCalculatorServiceImpl.calculate(null, 1, 2);
        }, "IllegalArgumentsException was expected");
        Assertions.assertEquals(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION, thrown.getMessage());

    }

    @Test
    public void testCalculateWithAddOperation() {
        Integer result = integerCalculatorServiceImpl.calculate(Operation.ADD, 1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testCalculateWithAddOperationAndIntegerOverflow() {
        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, Integer.MAX_VALUE, 1);
                },
                "ArithmeticException was expected");
        Assertions.assertEquals(Constants.INTEGER_OVERFLOW_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithSubtractOperation() {
        Assertions.assertEquals(-2, integerCalculatorServiceImpl.calculate(Operation.SUBTRACT, 3, 5));
    }

    @Test
    public void testCalculateWithMultiplyOperation() {
        Integer result = integerCalculatorServiceImpl.calculate(Operation.MULTIPLY, 3, 5);
        Assertions.assertEquals(15, result);
    }

    @Test
    public void testCalculateWithMultiplyOperationAndIntegerOverflow() {
        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.MULTIPLY, Integer.MAX_VALUE, 10);
                },
                "ArithmeticException was expected");
        Assertions.assertEquals(Constants.INTEGER_OVERFLOW_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithDivideOperation() {
        Integer result = integerCalculatorServiceImpl.calculate(Operation.DIVIDE, 5, 2);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testCalculateWithDivideOperationAndZeroDivisor() {
        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.DIVIDE, 14, 0);
                },
                "ArithmeticException was expected");
        Assertions.assertEquals("/ by zero", thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullInitialValue() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(null, new ArrayList<Operation>(List.of(Operation.ADD)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "IllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullOperationList() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(5, null, new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "IllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullArgumentsLists() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(8, new ArrayList<Operation>(List.of(Operation.ADD)), null);
                }, "IllegalArgumentException was expected");
        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithInvalidOperationListSize() {
        IllegalArgumentException thrown = Assertions
                .assertThrows(IllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(8, new ArrayList<Operation>(List.of(Operation.ADD)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "IllegalArgumentException was expected");
        Assertions.assertEquals(Constants.NUMBER_OF_CHAIN_OPERATIONS_NOT_EQUAL_NUMBER_OF_CHAIN_ARGUMENTS_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChain() {
        Integer result = integerCalculatorServiceImpl.calculateChain(8, new ArrayList<>(Arrays.asList(Operation.ADD, Operation.MULTIPLY, Operation.DIVIDE)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        Assertions.assertEquals(6, result);
    }
}
