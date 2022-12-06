package com.example.demo.independent;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(getterVisibility= JsonAutoDetect.Visibility.NONE)
public class CalculationBodyDTO {
    public List<Integer> arguments;
    public String operation;


    public List<Integer> getArguments() {
        return arguments;
    }

    public String getOperation() {
        return operation;
    }
}
