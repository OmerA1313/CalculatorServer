package com.example.demo.independent;

public class CalcResultDTO {
    private Integer result;
    private String errorMessage;


    public CalcResultDTO(Integer i_result) {
        result = i_result;
    }

    public CalcResultDTO(String i_errorMessage) {
        errorMessage = i_errorMessage;
    }

    public Integer getResult() {
        return result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setResult(Integer i_result) {
        result = i_result;
    }

    public void setErrorMessage(String i_errorMessage) {
        errorMessage = i_errorMessage;
    }
}
