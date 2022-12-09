package com.example.demo.stack;

import com.example.demo.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackManager {

    private static Stack<Integer> argumentsStack = new Stack<>();

    public static int getStackSize() {
        return argumentsStack.size();
    }

    public static void addArguments(List<Integer> i_arguments) {
        i_arguments.stream().forEach(arg -> argumentsStack.push(arg));
    }

    public static int deleteArguments(Integer i_count) throws ResponseStatusException {
        if (getStackSize() >= i_count) {
            int i = 0;
            while (i < i_count) {
                argumentsStack.pop();
                i++;
            }
        }
        else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(409), String.format("Error: cannot remove %s from the stack. It has only %s arguments", i_count, getStackSize()));
        }
        return getStackSize();
    }

    public static List<Integer> tryExtractArgumentsByOperation(String i_operation) throws ResponseStatusException {
        List<Integer> extractedArguments = new ArrayList<>();
        boolean invalidArgumentCount = false;
        int requiredArgsAmount = 0;
        if (CalculatorService.isUnaryOperation(i_operation)) {
            requiredArgsAmount = 1;
            if (getStackSize() >= requiredArgsAmount) {
                extractedArguments.add(argumentsStack.pop());
            } else {
                invalidArgumentCount = true;
            }
        } else if (CalculatorService.isBinaryOperation(i_operation)) {
            requiredArgsAmount = 2;
            if (getStackSize() >= requiredArgsAmount) {
                extractedArguments.add(argumentsStack.pop());
                extractedArguments.add(argumentsStack.pop());
            } else {
                invalidArgumentCount = true;
            }
        }
        if (invalidArgumentCount) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    String.format("Error: cannot implement operation %s. It requires %s arguments and the stack has only %s arguments", i_operation, requiredArgsAmount,getStackSize()));
        }

        return extractedArguments;
    }
}

