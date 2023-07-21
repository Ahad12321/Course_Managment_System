package com.example.paymentsapp.exception.CustomEx;

public class ExpensesNotFoundException extends RuntimeException {
    public ExpensesNotFoundException(String s) {
        super(s);
    }
}
