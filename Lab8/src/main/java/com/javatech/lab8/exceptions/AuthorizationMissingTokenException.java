package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomUnauthorizedException;

public class AuthorizationMissingTokenException extends CustomUnauthorizedException {
    public AuthorizationMissingTokenException() {
        this.message = "Authorization token is required but it is missing";
    }
}
