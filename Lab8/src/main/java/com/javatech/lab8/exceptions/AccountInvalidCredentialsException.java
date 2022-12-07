package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomUnauthorizedException;

public class AccountInvalidCredentialsException extends CustomUnauthorizedException {
    public AccountInvalidCredentialsException() {
        this.message = "Account credentials are invalid";
    }

}
