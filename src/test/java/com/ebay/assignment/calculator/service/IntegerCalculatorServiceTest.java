package com.ebay.assignment.calculator.service;

import com.ebay.assignment.calculator.exception.CalculatorArithmeticException;
import com.ebay.assignment.calculator.exception.CalculatorIllegalArgumentException;
import com.ebay.assignment.calculator.exception.CalculatorNotSupportedOperationException;
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
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, null, null);
                }, "CalculatorIllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithFirstArgumentNull() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, null, 5);
                }, "CalculatorIllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithSecondArgumentNull() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, 5, null);
                }, "CalculatorIllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithOperationTypeNull() {
        CalculatorIllegalArgumentException thrown = Assertions.assertThrows(CalculatorIllegalArgumentException.class, () -> {
            integerCalculatorServiceImpl.calculate(null, 1, 2);
        }, "CalculatorIllegalArgumentException was expected");
        Assertions.assertEquals(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithNotSupportedOperation() {
        CalculatorNotSupportedOperationException thrown = Assertions.assertThrows(CalculatorNotSupportedOperationException.class, () -> {
            integerCalculatorServiceImpl.calculate(Operation.NOT_SUPPORTED, 1, 2);
        }, "CalculatorNotSupportedOperation was expected");
        Assertions.assertEquals(Constants.OPERATION_NOT_SUPPORTED_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithAddOperation() {
        Integer result = integerCalculatorServiceImpl.calculate(Operation.ADD, 1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    public void testCalculateWithAddOperationAndIntegerOverflow() {
        CalculatorArithmeticException thrown = Assertions.assertThrows(CalculatorArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.ADD, Integer.MAX_VALUE, 1);
                },
                "CalculatorArithmeticException was expected");
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
        CalculatorArithmeticException thrown = Assertions.assertThrows(CalculatorArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.MULTIPLY, Integer.MAX_VALUE, 10);
                },
                "CalculatorArithmeticException was expected");
        Assertions.assertEquals(Constants.INTEGER_OVERFLOW_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateWithDivideOperation() {
        Integer result = integerCalculatorServiceImpl.calculate(Operation.DIVIDE, 5, 2);
        Assertions.assertEquals(2, result);
    }

    @Test
    public void testCalculateWithDivideOperationAndZeroDivisor() {
        CalculatorArithmeticException thrown = Assertions.assertThrows(CalculatorArithmeticException.class, () -> {
                    integerCalculatorServiceImpl.calculate(Operation.DIVIDE, 14, 0);
                },
                "CalculatorArithmeticException was expected");
        Assertions.assertEquals("/ by zero", thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullInitialValue() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(null, new ArrayList<Operation>(List.of(Operation.ADD)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "CalculatorIllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullOperationList() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(5, null, new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "CalculatorIllegalArgumentException was expected");

        Assertions.assertEquals(Constants.OPERATION_CAN_NOT_BE_NULL_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithNullArgumentsLists() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(8, new ArrayList<Operation>(List.of(Operation.ADD)), null);
                }, "CalculatorIllegalArgumentException was expected");
        Assertions.assertEquals(Constants.OPERANDS_MUST_HAVE_NOT_NULL_VALUES_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChainWithInvalidOperationListSize() {
        CalculatorIllegalArgumentException thrown = Assertions
                .assertThrows(CalculatorIllegalArgumentException.class, () -> {
                    integerCalculatorServiceImpl.calculateChain(8, new ArrayList<Operation>(List.of(Operation.ADD)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
                }, "CalculatorIllegalArgumentException was expected");
        Assertions.assertEquals(Constants.NUMBER_OF_CHAIN_OPERATIONS_NOT_EQUAL_NUMBER_OF_CHAIN_ARGUMENTS_EXCEPTION, thrown.getMessage());
    }

    @Test
    public void testCalculateChain() {
        Integer result = integerCalculatorServiceImpl.calculateChain(8, new ArrayList<>(Arrays.asList(Operation.ADD, Operation.MULTIPLY, Operation.DIVIDE)), new ArrayList<Integer>(Arrays.asList(1, 2, 3)));
        Assertions.assertEquals(6, result);
    }
}
