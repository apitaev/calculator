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

import java.util.ArrayList;
import java.util.List;

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
        if (ArgumentsType.DOUBLE.name().equals(argumentsType)) {
            return doubleCalculatorServiceImpl.calculate(Operation.ADD, firstSummand.doubleValue(), secondSummand.doubleValue());
        }
        if (ArgumentsType.INTEGER.name().equals(argumentsType)) {
            return integerCalculatorServiceImpl.calculate(Operation.ADD, firstSummand.intValue(), secondSummand.intValue());
        }
        throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

    @GetMapping("/subtract")
    public Number subtract(@RequestParam Number minuend, @RequestParam Number subtrahend, @RequestParam String argumentsType) {
        if (ArgumentsType.DOUBLE.name().equals(argumentsType))
            return doubleCalculatorServiceImpl.calculate(Operation.SUBTRACT, minuend.doubleValue(), subtrahend.doubleValue());
        if (ArgumentsType.INTEGER.name().equals(argumentsType))
            return integerCalculatorServiceImpl.calculate(Operation.SUBTRACT, minuend.intValue(), subtrahend.intValue());
        throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

    @GetMapping("/multiply")
    public Number multiply(@RequestParam Number multiplicand, @RequestParam Number multiplier, @RequestParam String argumentsType) {
        if (ArgumentsType.DOUBLE.name().equals(argumentsType))
            return doubleCalculatorServiceImpl.calculate(Operation.MULTIPLY, multiplicand.doubleValue(), multiplier.doubleValue());
        if (ArgumentsType.INTEGER.name().equals(argumentsType))
            return integerCalculatorServiceImpl.calculate(Operation.MULTIPLY, multiplicand.intValue(), multiplier.intValue());
        throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

    @GetMapping("/divide")
    public Number divide(@RequestParam Number dividend, @RequestParam Number divisor, @RequestParam String argumentsType) {
        if (ArgumentsType.DOUBLE.name().equals(argumentsType))
            return doubleCalculatorServiceImpl.calculate(Operation.DIVIDE, dividend.doubleValue(), divisor.doubleValue());
        if (ArgumentsType.INTEGER.name().equals(argumentsType))
            return integerCalculatorServiceImpl.calculate(Operation.DIVIDE, dividend.intValue(), divisor.intValue());
        throw new CalculatorIllegalArgumentException(Constants.ARGUMENTS_TYPE_NOT_SUPPORTED_EXCEPTION);
    }

    @GetMapping("/calculateChain")
    public Number calculateChain(@RequestParam Number initialValue, @RequestParam List<Operation> operationList, @RequestParam List<Number> numbers, @RequestParam String argumentsType) {
        if (ArgumentsType.DOUBLE.name().equals(argumentsType)) {
            List<Double> secondArguments = new ArrayList<>();
            for (Number secondArgument : numbers) {
                secondArguments.add(secondArgument.doubleValue());
            }
            return doubleCalculatorServiceImpl.calculateChain(initialValue.doubleValue(), operationList, secondArguments);
        }
        if (ArgumentsType.INTEGER.name().equals(argumentsType)) {
            List<Integer> secondArguments = new ArrayList<>();
            for (Number secondArgument : numbers) {
                secondArguments.add(secondArgument.intValue());
            }
            return integerCalculatorServiceImpl.calculateChain(initialValue.intValue(), operationList, secondArguments);
        }
        //TODO add rest api exception here
        return null;
    }
}



