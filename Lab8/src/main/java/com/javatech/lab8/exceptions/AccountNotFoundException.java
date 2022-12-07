package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomNotFoundException;

public class AccountNotFoundException extends CustomNotFoundException {
    public AccountNotFoundException() {
        this.message = "Account with the submit credentials does not exists";
    }
}
