package com.javatech.lab8.controller;

import com.javatech.lab8.annotations.JWTTokenRequired;
import com.javatech.lab8.dtos.*;
import com.javatech.lab8.pemissions.Role;
import com.javatech.lab8.service.DocumentService;
import com.javatech.lab8.utils.ResponseEntityPayload;
import com.javatech.lab8.utils.ResponsePayload;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.Collections;
import java.util.List;

@Path("/documents")
@ApplicationScoped
public class DocumentController {

    @Inject
    DocumentService documentService;

    @GET
    @Produces("application/json")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.ADMIN})
    public Response getDocuments() {

        List<DocumentDTO> documents = documentService.gets();

        ResponseEntityPayload<DocumentDTO> entity = new ResponseEntityPayload<>(
                "OK",
                "Documents fetched succesfully",
                documents);

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @GET
    @Path("/{documentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    public Response getDocument(@PathParam("documentId") Long documentId) {

        DocumentDTO document = documentService.get(documentId);

        ResponseEntityPayload<DocumentDTO> entity = new ResponseEntityPayload<>(
                "OK",
                "Document fetched successfully",
                Collections.singletonList(document)
        );

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    public Response getPersonalDocuments(@Context SecurityContext securityContext) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        List<DocumentDTO> documents = documentService.getPersonal(id);

        ResponseEntityPayload<DocumentDTO> entity = new ResponseEntityPayload<DocumentDTO>(
                "OK",
                "Personal documents fetched successfully",
                documents
        );

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    public Response addDocument(@Context SecurityContext securityContext, DocumentAddDTO document) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());
        documentService.create(document, id);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Document created successfully!");

        return Response
                .status(Response.Status.CREATED)
                .entity(payload)
                .build();

    }

    @POST
    @Path("/{docId}/authors")
    @Produces("application/json")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    public Response addAuthorToDocument(@Context SecurityContext securityContext,
                                        @PathParam("docId") Long documentId,
                                        DocumentAddAuthorDTO author) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        documentService.addAuthorToDocument(documentId, author.getAuthorId(), id);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Document's author added successfully!");

        return Response
                .status(Response.Status.CREATED)
                .entity(payload)
                .build();
    }

    @DELETE
    @Path("/{docId}")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeDocument(@Context SecurityContext securityContext, @PathParam("docId") Long documentId) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        documentService.remove(documentId, id);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Document removed successfully!");

        return Response
                .status(Response.Status.CREATED)
                .entity(payload)
                .build();

    }

    @DELETE
    @Path("/{docId}/authors/{authorId}")
    @Produces("application/json")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.AUTHOR, Role.REVIEWER})
    public Response removeAuthorFromDocument(@Context SecurityContext securityContext,
                                             @PathParam("docId") Long documentId,
                                             @PathParam("authorId") Long authorId) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        documentService.removeAuthorFromDocument(documentId, id, authorId);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Document's author removed successfully!");

        return Response
                .status(Response.Status.CREATED)
                .entity(payload)
                .build();
    }

    @POST
    @Path("/{documentId}/references/")
    @Consumes("application/json")
    @Produces("application/json")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.ADMIN, Role.REVIEWER})
    public Response addReferenceToDocument(@Context SecurityContext securityContext,
                                           @PathParam("documentId") long documentId,
                                           DocumentAddReferenceDTO reference) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        documentService.addReferenceToDocument(documentId, id, reference);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Reference added successfully");

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }

    @DELETE
    @Path("/{documentId}/references/{referenceId}")
    @Consumes("application/json")
    @Produces("application/json")
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.ADMIN, Role.REVIEWER})
    public Response deleteReferenceFromDocument(@Context SecurityContext securityContext,
                                                @PathParam("documentId") long documentId,
                                                @PathParam("referenceId") long referenceId) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        documentService.removeReferenceFromDocument(documentId, referenceId, id);

        ResponsePayload payload = new ResponsePayload(
                "OK",
                "Document's reference deleted successfully");

        return Response
                .status(Response.Status.OK)
                .entity(payload)
                .build();
    }

    @GET
    @Path("/{documentId}/references")
    @Produces(MediaType.APPLICATION_JSON)
    @JWTTokenRequired(Permissions = {Role.AUTHOR, Role.ADMIN, Role.REVIEWER})
    public Response getReferencesFromDocument(@Context SecurityContext securityContext,
                                              @PathParam("documentId") long documentId) {

        Long id = Long.valueOf(securityContext.getUserPrincipal().getName());

        List<DocumentReferenceDTO> references = documentService.getReferencesOfDocument(documentId, id);

        ResponseEntityPayload<DocumentReferenceDTO> entity = new ResponseEntityPayload<>(
                "OK",
                "Document's references fetched successfully",
                references);

        return Response
                .status(Response.Status.OK)
                .entity(entity)
                .build();
    }

}
