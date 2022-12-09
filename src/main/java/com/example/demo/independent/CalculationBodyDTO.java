package com.example.demo.independent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(getterVisibility= JsonAutoDetect.Visibility.NONE)
public class CalculationBodyDTO {
    public List<Integer> arguments;
    public String operation;

    public <E> CalculationBodyDTO(String i_operation, List<Integer> i_arguments) {
        operation = i_operation;
        arguments = i_arguments;
    }


    public List<Integer> getArguments() {
        return arguments;
    }

    public String getOperation() {
        return operation;
    }
}
