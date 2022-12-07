package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomBadRequestException;

public class DocumentCircularReferenceException extends CustomBadRequestException {
    public DocumentCircularReferenceException() {
        this.message = "Adding specified reference to the document will create a circular reference";
    }
}
