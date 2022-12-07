package com.javatech.lab8.exceptions.mappers;

import com.javatech.lab8.utils.ResponsePayload;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class CustomNoResultExceptionMapper implements ExceptionMapper<NoResultException> {

    @Override
    public Response toResponse(NoResultException e) {
        ResponsePayload payload = new ResponsePayload(
                "FAILED",
                e.getMessage()
        );

        return Response
                .status(Response.Status.METHOD_NOT_ALLOWED)
                .entity(payload)
                .build();

    }
}
