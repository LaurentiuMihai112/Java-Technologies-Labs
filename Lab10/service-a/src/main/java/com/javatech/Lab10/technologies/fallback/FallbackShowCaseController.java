/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.fallback;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 * @author Laurentiu
 */
@Path("/fallback")
@Singleton
public class FallbackShowCaseController {

    @Inject
    private FallbackShowCaseService fallbackService;

    @GET
    public String fallbackEndpoint() throws Exception {
        return "Value is: " + fallbackService.getFallback();
    }
}
