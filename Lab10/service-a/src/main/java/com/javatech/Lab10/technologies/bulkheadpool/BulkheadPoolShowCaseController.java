/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.bulkheadpool;

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
public class BulkheadPoolShowCaseController {

    @Inject
    private BulkheadPoolShowCaseService bulkheadService;

    @GET
    @Bulkhead(value = 3, waitingTaskQueue = 5) // maximum 2 concurrent requests allowed, with a waiting pool of 3 requests
    @Fallback(fallbackMethod = "fallbackMethod")
    public String bulkheadThreadpoolEndpoint() throws Exception {
        return "Time value is: " + bulkheadService.getBulkhead();
    }

    private String fallbackMethod() {
        return "Error on bulkhead-pool Endpoint";
    }
}
