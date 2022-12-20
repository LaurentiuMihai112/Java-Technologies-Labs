/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javatech.Lab10.technologies.bulkhead;

import java.time.LocalDateTime;

/**
 *
 * @author Laurentiu
 */
public class BulkheadShowCaseService {

    public String getBulkhead() throws Exception {
        Thread.sleep(3000);
        return LocalDateTime.now().toString();
    }
}
