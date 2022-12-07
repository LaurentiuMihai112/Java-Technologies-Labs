package com.javatech.lab8.exceptions.mappers;

import com.javatech.lab8.exceptions.http.CustomNotFoundException;
import com.javatech.lab8.utils.ResponsePayload;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomNotFoundMapper implements ExceptionMapper<CustomNotFoundException> {
    @Override
    public Response toResponse(CustomNotFoundException e) {
        ResponsePayload payload = new ResponsePayload(
                "FAILED",
                e.getMessage());

        return Response
                .status(Response.Status.NOT_FOUND.getStatusCode())
                .entity(payload)
                .build();
    }
}
