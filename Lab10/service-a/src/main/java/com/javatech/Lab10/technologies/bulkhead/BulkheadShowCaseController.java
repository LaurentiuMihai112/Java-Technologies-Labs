/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.bulkhead;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.Fallback;

/**
 *
 * @author Laurentiu
 */
@Path("/bulkhead-pool")
@Singleton
public class BulkheadShowCaseController {

    @Inject
    private BulkheadShowCaseService bulkheadService;

    @GET
    @Bulkhead(1) // maximum 1 concurrent requests allowed
    @Fallback(fallbackMethod = "fallbackMethod")
    public String bulkheadEndpoint() throws Exception {
        return "Value is: " + bulkheadService.getBulkhead();
    }

    private String fallbackMethod() {
        return "Error on bulkhead Endpoint";
    }
}
