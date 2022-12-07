package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomUnauthorizedException;

public class DocumentInvalidOwnershipException extends CustomUnauthorizedException {
    public DocumentInvalidOwnershipException() {
        this.message = "Specified account does not have right over the specified document";
    }
}
