package com.javatech.lab8.exceptions.http;

import javax.ejb.ApplicationException;

@ApplicationException
public abstract class CustomException extends RuntimeException {
    protected int http;
    protected String message;
    protected String code;

    public int getHttpCode() {
        return http;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public String toJSON() {
        return null;
    }
}
