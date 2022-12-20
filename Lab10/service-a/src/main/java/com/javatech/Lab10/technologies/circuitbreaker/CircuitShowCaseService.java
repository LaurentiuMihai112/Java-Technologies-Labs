/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.circuitbreaker;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;

/**
 *
 * @author Laurentiu
 */
@Singleton
public class CircuitShowCaseService {

    private int count = 0;

    @CircuitBreaker(successThreshold = 3, requestVolumeThreshold = 1, failureRatio = 1, delay = 2000)
    @Fallback(fallbackMethod = "fallbackMethod")
    public String getBreaker() throws Exception {
        if (count >= 3) {
            throw new Exception("Break");
        }
        count += 1;
        return "Count = " + count;
    }

    public String fallbackMethod() {
        return "CIRCUIT BREAKER IS OPEN";
    }
}
