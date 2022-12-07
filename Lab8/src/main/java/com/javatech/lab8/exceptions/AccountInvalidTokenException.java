package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomUnauthorizedException;

public class AccountInvalidTokenException extends CustomUnauthorizedException {
    public AccountInvalidTokenException() {
        this.message = "Provided token is invalid";
    }
}
