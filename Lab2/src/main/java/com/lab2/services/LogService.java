package com.lab2.services;

import jakarta.servlet.ServletRequest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogService {
    public static void logRequest(ServletRequest request) {
        try {
            var myWriter = new FileWriter("D:\\Master\\Java-Technologies-Labs\\Lab2\\log.txt",true);
            var bw = new BufferedWriter(myWriter);
            var log_text = "";
            log_text += "IP: " + request.getRemoteAddr() + ", Time: " + new Date() + "\n";
            log_text += "Content Type: " + request.getContentType() + ", Encoding: " + request.getCharacterEncoding() + "\n\n";
            bw.write(log_text);
            bw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
