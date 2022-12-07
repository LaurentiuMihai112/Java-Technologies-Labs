package com.javatech.lab8.exceptions.mappers;

import com.javatech.lab8.exceptions.http.CustomBadRequestException;
import com.javatech.lab8.utils.ResponsePayload;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomBadRequestMapper implements ExceptionMapper<CustomBadRequestException> {
    @Override
    public Response toResponse(CustomBadRequestException e) {
        ResponsePayload payload = new ResponsePayload(
                "FAILED",
                e.getMessage()
        );

        return Response
                .status(Response.Status.CONFLICT)
                .entity(payload)
                .build();
    }
}
