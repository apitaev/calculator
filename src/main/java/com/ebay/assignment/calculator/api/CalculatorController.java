package com.ebay.assignment.calculator.api;

import com.ebay.assignment.calculator.exception.CalculatorIllegalArgumentException;
import com.ebay.assignment.calculator.service.ArgumentsType;
import com.ebay.assignment.calculator.service.CalculatorService;
import com.ebay.assignment.calculator.service.Constants;
import com.ebay.assignment.calculator.service.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final CalculatorService<Double> doubleCalculatorServiceImpl;
    private final CalculatorService<Integer> integerCalculatorServiceImpl;

    @Autowired
    public CalculatorController(CalculatorService<Integer> integerCalculatorServiceImpl, CalculatorService<Double> doubleCalculatorServiceImpl) {
        this.integerCalculatorServiceImpl = integerCalculatorServiceImpl;
        this.doubleCalculatorServiceImpl = doubleCalculatorServiceImpl;
    }

    @GetMapping("/")
    public String hello() {
        return "Greetings from Calculator Service!";
    }

    @GetMapping("/add")
    public Number add(@RequestParam Number firstSummand, @RequestParam Number secondSummand, @RequestParam String argumentsType) {
        try {
            return switch (ArgumentsType.valueOf(argumentsType)) {
                case DOUBLE ->
                        doubleCalculatorServiceImpl.calculate(Operation.ADD, firstSummand.doubleValue(), secondSummand.doubleValue());
                case INTEGER ->
                        integerCalculatorServiceImpl.calculate(Operation.ADD, firstSummand.intValue(), secondSummand.intValue());
            };
        } catch (IllegalArgumentException e) {
            throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
        }
    }


    @GetMapping("/subtract")
    public Number subtract(@RequestParam Number minuend, @RequestParam Number subtrahend, @RequestParam String argumentsType) {
        try {
            return switch (ArgumentsType.valueOf(argumentsType)) {
                case DOUBLE ->
                        doubleCalculatorServiceImpl.calculate(Operation.SUBTRACT, minuend.doubleValue(), subtrahend.doubleValue());
                case INTEGER ->
                        integerCalculatorServiceImpl.calculate(Operation.SUBTRACT, minuend.intValue(), subtrahend.intValue());
            };
        } catch (IllegalArgumentException e) {
            throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
        }
    }

    @GetMapping("/multiply")
    public Number multiply(@RequestParam Number multiplicand, @RequestParam Number multiplier, @RequestParam String argumentsType) {

        try {
            return switch (ArgumentsType.valueOf(argumentsType)) {
                case DOUBLE ->
                        doubleCalculatorServiceImpl.calculate(Operation.MULTIPLY, multiplicand.doubleValue(), multiplier.doubleValue());
                case INTEGER ->
                        integerCalculatorServiceImpl.calculate(Operation.MULTIPLY, multiplicand.intValue(), multiplier.intValue());
            };
        } catch (IllegalArgumentException e) {
            throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
        }
    }

    @GetMapping("/divide")
    public Number divide(@RequestParam Number dividend, @RequestParam Number divisor, @RequestParam String argumentsType) {
        try {
            return switch (ArgumentsType.valueOf(argumentsType)) {
                case DOUBLE ->
                        doubleCalculatorServiceImpl.calculate(Operation.DIVIDE, dividend.doubleValue(), divisor.doubleValue());
                case INTEGER ->
                        integerCalculatorServiceImpl.calculate(Operation.DIVIDE, dividend.intValue(), divisor.intValue());
            };
        } catch (IllegalArgumentException e) {
            throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
        }
    }

    @GetMapping("/calculateChain")
    public Number calculateChain(@RequestParam Number initialValue, @RequestParam List<Operation> operationList, @RequestParam List<Number> numbers, @RequestParam String argumentsType) {
        try {
            return switch (ArgumentsType.valueOf(argumentsType)) {
                case DOUBLE ->
                        doubleCalculatorServiceImpl.calculateChain(initialValue.doubleValue(), operationList, numbers.stream().map(Number::doubleValue).collect(Collectors.toList()));

                case INTEGER ->
                        integerCalculatorServiceImpl.calculateChain(initialValue.intValue(), operationList, numbers.stream().map(Number::intValue).collect(Collectors.toList()));
            };
        } catch (IllegalArgumentException e) {
            throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
        }
    }
}



