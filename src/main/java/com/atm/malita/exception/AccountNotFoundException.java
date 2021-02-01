package com.atm.malita.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String message) {
        super("Account with id: \"" + message + "\" not found");
    }
}
