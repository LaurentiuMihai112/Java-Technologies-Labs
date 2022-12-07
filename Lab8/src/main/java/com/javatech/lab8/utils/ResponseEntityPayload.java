package com.javatech.lab8.utils;

import java.util.List;

public class ResponseEntityPayload<T> {
    private final String message;
    private final List<T> data;
    private final String status;

    public ResponseEntityPayload(String status, String message, List<T> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<T> getData() {
        return data;
    }


}
