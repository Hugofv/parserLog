package com.ef.service;

import com.ef.ValueObject.Options;
import com.ef.model.Log;
import com.ef.repository.LogDAO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogService {

    private LogDAO logDAO = new LogDAO();

    public void readLog(Options options) {

        String line;
        String[] partLine;
        int numLine = 0;
        Log log = new Log();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");


        try {
            FileInputStream fileInputStream = new FileInputStream(options.getAcessLog());
            BufferedReader file = new BufferedReader(new InputStreamReader(fileInputStream));

            while ((line = file.readLine()) != null) {
                partLine = line.split("\\|");

                if(partLine[0] != null) {
                    log.setStartDate(LocalDateTime.parse(partLine[0], formatter));
                } else {
                    System.out.println("Error in startDate of line: " + numLine + ", Verify.");
                }

                if(partLine[1] != null) {
                    log.setIpAddress(partLine[1].replaceAll("^\"|\"$", ""));
                } else {
                    System.out.println("Error in IP of line: " + numLine + ", Verify.");
                }

                if(partLine[2] != null) {
                    log.setRequest(partLine[2].replaceAll("^\"|\"$", ""));
                } else {
                    System.out.println("Error in request of line: " + numLine + ", Verify.");
                }

                if(partLine[3] != null) {
                    log.setStatusCode(Integer.parseInt(partLine[3].replaceAll("^\"|\"$", "")));
                } else {
                    System.out.println("Error in status code of line: " + numLine + ", Verify.");
                }

                if(partLine[4] != null) {
                    log.setUserAgent(partLine[4].replaceAll("^\"|\"$", ""));
                } else {
                    System.out.println("Error in user agent of line: " + numLine + ", Verify.");
                }

                logDAO.insert(log);
                numLine++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
