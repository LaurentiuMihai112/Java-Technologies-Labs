package com.javatech.lab8.controller;

import com.javatech.lab8.annotations.JWTAuthRequired;
import com.javatech.lab8.dtos.AccountDTO;
import com.javatech.lab8.dtos.AccountLoginDTO;
import com.javatech.lab8.dtos.AccountRegisterDTO;
import com.javatech.lab8.pemissions.Role;
import com.javatech.lab8.service.AccountService;
import com.javatech.lab8.tokens.TokenHandler;
import com.javatech.lab8.utils.ResponseEntityPayload;
import com.javatech.lab8.utils.ResponsePayload;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;

@Path("/users")
@RequestScoped
public class UserController {
    @Inject
    AccountService accountService;

    @GET
    @Produces("application/json")
    @JWTAuthRequired(Permissions = {
            Role.AUTHOR, Role.ADMIN, Role.REVIEWER})
    public Response getAccounts() {
        List<AccountDTO> accounts = accountService.gets();

        ResponseEntityPayload<AccountDTO> entity = new ResponseEntityPayload<>(
                "OK",
                "Accounts fetched successfully",
                accounts);

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();

    }

    @GET
    @Path("/{accountId}")
    @Produces("application/json")
    @JWTAuthRequired(Permissions = {
            Role.AUTHOR, Role.ADMIN, Role.REVIEWER})
    public Response getAccount(@PathParam("accountId") Long accountId) {

        AccountDTO account = accountService.get(accountId);
        ResponseEntityPayload<AccountDTO> entity = new ResponseEntityPayload<>(
                "OK",
                "Account fetched successfully",
                Collections.singletonList(account));

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();

    }


    @POST
    @Path("/authenticate")
    @Consumes("application/json")
    @Produces("application/json")
    public Response authenticate(AccountLoginDTO user) {

        Long id = accountService.validate(user);

        String token = TokenHandler.issue(id);

        ResponseEntityPayload<String> payload = new ResponseEntityPayload<String>(
                "OK",
                "User logged in successfully",
                Collections.singletonList(token)
        );

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();

    }

    @POST
    @Path("/registerAuthor")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerAuthor(AccountRegisterDTO user) {

        accountService.create(user,Role.AUTHOR);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Author account created successfully!");

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }
    @POST
    @Path("/registerReviewer")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerReviewer(AccountRegisterDTO user) {

        accountService.create(user,Role.AUTHOR);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Reviewer account created successfully!");

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }
    @POST
    @Path("/registerAdmin")
    @Consumes("application/json")
    @Produces("application/json")
    public Response registerAdmin(AccountRegisterDTO user) {

        accountService.create(user,Role.AUTHOR);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Admin account created successfully!");

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }

    @DELETE
    @Path("/{id}")
    @JWTAuthRequired(Permissions = {
            Role.ADMIN})
    @Produces("application/json")
    public Response delete(@PathParam("id") long id) {


        accountService.remove(id);
        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Account successfully deleted"
        );

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }


}
