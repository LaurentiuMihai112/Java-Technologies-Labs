package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomUnauthorizedException;

public class AccountNotAllowedException extends CustomUnauthorizedException {
    public AccountNotAllowedException() {
        this.message = "The account owned by the user does not have permission level required";
    }
}
