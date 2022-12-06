package com.example.demo.independent;

import com.example.demo.Exceptions.ArgumentAmountException;
import com.example.demo.Exceptions.DivisioByZeroException;
import com.example.demo.Exceptions.OperationNotSupported;
import com.google.common.math.BigIntegerMath;
import org.springframework.http.HttpStatusCode;

import java.util.*;

public class OperationService { /* TODO refactor - separate operationService - responsible for executing arithmetic expression given arguments and operation
                                    and expression validator - interface holding tryValidate method, implemented differently for independent and stack. */
    private static CalculationBodyDTO calculationParams;
    private static final Set<String> supportedBinaryOperations = Set.of("plus", "minus", "times", "divide", "pow");
    private static final Set<String> supportedUnaryOperations = Set.of("fact","abs");

    public OperationService(CalculationBodyDTO i_calculationParams) {
        calculationParams = i_calculationParams;
    }

    public void tryValidateOperation()  {
        String operation = calculationParams.getOperation().toLowerCase();
        boolean notEnoughArgs = false;
        boolean tooManyArgs = false;
        int numOfArguments = calculationParams.getArguments().size();
        if (supportedBinaryOperations.contains(operation)) {
            if (numOfArguments > 2) {
                tooManyArgs = true;
            } else if (numOfArguments < 2) {
                notEnoughArgs = true;
            }
        } else if (supportedUnaryOperations.contains(operation)) {
            if (numOfArguments > 1) {
                tooManyArgs = true;
            } else if (numOfArguments < 1) {
                notEnoughArgs = true;
            }
        }
        else {
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
    public int execute() throws DivisioByZeroException {
        String operation = calculationParams.getOperation().toLowerCase();
        List<Integer> arguments = calculationParams.arguments;
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
