package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomConflictException;

public class DocumentAuthorAlreadyExistsException extends CustomConflictException {
    public DocumentAuthorAlreadyExistsException() {
        this.message = "Specified document's author does not exist";
    }
}
