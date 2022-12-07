package com.javatech.lab8.exceptions.mappers;

import com.javatech.lab8.exceptions.http.CustomServerErrorException;
import com.javatech.lab8.utils.ResponsePayload;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CustomServerErrorMapper implements ExceptionMapper<CustomServerErrorException> {
    @Override
    public Response toResponse(CustomServerErrorException e) {
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
