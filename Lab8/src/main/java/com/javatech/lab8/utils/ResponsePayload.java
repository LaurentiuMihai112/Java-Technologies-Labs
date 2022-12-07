package com.javatech.lab8.utils;

public class ResponsePayload {
    private final String status;
    private final String message;

    public ResponsePayload(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
