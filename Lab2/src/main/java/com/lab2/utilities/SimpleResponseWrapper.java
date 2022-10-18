package com.lab2.utilities;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.PrintWriter;
import java.io.StringWriter;

public class SimpleResponseWrapper extends HttpServletResponseWrapper {
    private final StringWriter output;

    public SimpleResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new StringWriter();
    }

    @Override
    public PrintWriter getWriter() {
        // Hide the original writer
        return new PrintWriter(output);
    }

    @Override
    public String toString() {
        return output.toString();
    }
}