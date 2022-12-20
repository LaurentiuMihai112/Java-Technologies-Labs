/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.fallback;

import jakarta.inject.Singleton;
import java.time.LocalDateTime;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

/**
 *
 * @author Laurentiu
 */
@Singleton
public class FallbackShowCaseService {

    @Retry(maxRetries = 1)
    @Timeout(2) // 2 ms
    @Fallback(fallbackMethod = "fallbackMethod")
    public String getFallback() {
        while (LocalDateTime.now().getSecond() % 10 != 1) {
            //do nothing
        }
        return LocalDateTime.now().toString();
    }

    public String fallbackMethod() {
        return "FALLBACK";
    }
}
