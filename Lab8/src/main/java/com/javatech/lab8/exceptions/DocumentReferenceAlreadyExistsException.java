package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomConflictException;

public class DocumentReferenceAlreadyExistsException extends CustomConflictException {
    public DocumentReferenceAlreadyExistsException() {
        this.message = "Specified reference for document already exist";
    }
}
