/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.bulkheadpool;

import jakarta.inject.Singleton;
import java.time.LocalDateTime;

/**
 *
 * @author Laurentiu
 */
@Singleton
public class BulkheadPoolShowCaseService {

    public String getBulkhead() throws Exception {
        Thread.sleep(3000);
        return LocalDateTime.now().toString();
    }
}
