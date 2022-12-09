package com.example.demo;

import com.example.demo.Exceptions.ArgumentAmountException;
import com.example.demo.Exceptions.DivisioByZeroException;
import com.example.demo.Exceptions.OperationNotSupported;
import com.google.common.math.BigIntegerMath;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

public class CalculatorService {

    private static final Set<String> supportedBinaryOperations = Set.of("plus", "minus", "times", "divide", "pow");
    private static final Set<String> supportedUnaryOperations = Set.of("fact", "abs");


    public static void tryValidateExpression(List<Integer> arguments, String operation) throws ResponseStatusException {
        operation = operation.toLowerCase();
        boolean notEnoughArgs = false;
        boolean tooManyArgs = false;
        int numOfArguments = arguments.size();
        if (isBinaryOperation(operation)) {
            if (numOfArguments > 2) {
                tooManyArgs = true;
            } else if (numOfArguments < 2) {
                notEnoughArgs = true;
            }
        } else if (isUnaryOperation(operation)) {
            if (numOfArguments > 1) {
                tooManyArgs = true;
            } else if (numOfArguments < 1) {
                notEnoughArgs = true;
            }
        } else {
            throw new OperationNotSupported(HttpStatusCode.valueOf(409), "Error: unknown operation: " + operation);
        }
        String errorMessage = "";
        if (tooManyArgs) {
            errorMessage = "Error: Too many arguments to perform the operation " + operation;
        }
        if (notEnoughArgs) {
            errorMessage = "Error: Not enough arguments to perform the operation " + operation;
        }
        if (tooManyArgs || notEnoughArgs) {
            throw new ArgumentAmountException(HttpStatusCode.valueOf(409), errorMessage);
        }
    }

    public static boolean isUnaryOperation(String operation) {
        return supportedUnaryOperations.contains(operation);
    }

    public static boolean isBinaryOperation(String operation) {
        return supportedBinaryOperations.contains(operation);
    }
    public static int execute(List<Integer> arguments, String operation) throws DivisioByZeroException {
        Integer firstArgument = arguments.get(0);
        Integer secondArgument = arguments.size() == 2? arguments.get(1) : null;
        Integer result = null;
        switch(operation){
            case "plus":
                result = firstArgument + secondArgument;
                break;
            case "minus":
                result = firstArgument - secondArgument;
                break;
            case "times":
                result = firstArgument * secondArgument;
                break;
            case "divide":
                if (secondArgument == 0) {
                    throw new DivisioByZeroException(HttpStatusCode.valueOf(409), "Error while performing operation Divide: division by 0");
                }
                result = firstArgument / secondArgument;
                break;
            case "pow":
                result = Double.valueOf(Math.pow(firstArgument, secondArgument)).intValue();
                break;
            case "abs":
                result = Math.abs(firstArgument);
                break;
            case "fact":
                if (firstArgument < 0) {
                    throw new OperationNotSupported(HttpStatusCode.valueOf(409), "Error while performing operation Factorial: not supported for the negative number");
                }
                result = BigIntegerMath.factorial(firstArgument).intValue();
                break;
        }

        return result;
    }

}
