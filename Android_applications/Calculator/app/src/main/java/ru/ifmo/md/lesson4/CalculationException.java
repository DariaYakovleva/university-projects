package ru.ifmo.md.lesson4;

public class CalculationException extends Exception {
    private String error;
    CalculationException(String e) {
        error = e;
    }
    public String getMessage() {
        return error;
    }
}
