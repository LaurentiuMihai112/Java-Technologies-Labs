/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.circuitbreaker;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 * @author Laurentiu
 */
@Path("/circuit-breaker")
@Singleton
public class CircuitBreakerShowCaseController {

    @Inject
    private CircuitShowCaseService circuitBreakerService;

    @GET
    public String getCircuitBreakerEndpoint() throws Exception {
        return circuitBreakerService.getBreaker();
    }
}
