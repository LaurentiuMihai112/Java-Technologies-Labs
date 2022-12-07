package com.javatech.lab8.exceptions;

import com.javatech.lab8.exceptions.http.CustomNotFoundException;

public class DocumentAuthorNotFoundException extends CustomNotFoundException {
    public DocumentAuthorNotFoundException() {
        this.message = "Specified document's author does not exist";
    }
}
