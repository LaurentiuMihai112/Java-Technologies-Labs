package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomConflictException;


public class AccountAlreadyExistsException extends CustomConflictException {

    public AccountAlreadyExistsException() {
        this.message = "Account exists with credentials";
    }
}
