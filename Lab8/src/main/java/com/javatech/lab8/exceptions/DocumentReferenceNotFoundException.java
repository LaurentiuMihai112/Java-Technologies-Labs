package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomNotFoundException;

public class DocumentReferenceNotFoundException extends CustomNotFoundException {
    public DocumentReferenceNotFoundException() {
        this.message = "Specified document's reference does not exists";
    }
}
