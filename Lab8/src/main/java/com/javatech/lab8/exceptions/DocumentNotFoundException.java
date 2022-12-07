package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomNotFoundException;

public class DocumentNotFoundException extends CustomNotFoundException {
    public DocumentNotFoundException() {
        this.message = "Requested document does not exist";
    }
}
