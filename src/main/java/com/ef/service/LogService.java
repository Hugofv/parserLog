package com.ef.service;

import com.ef.ValueObject.Options;
import com.ef.model.FileLog;
import com.ef.model.IpBlocked;
import com.ef.model.Log;
import com.ef.repository.LogDAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class LogService {

    private LogDAO logDAO = new LogDAO();

    public void readLog(Options options) {

        String line;
        String[] partLine;
        int fileId;
        Log log = new Log();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            FileLog fileLog = new FileLog();
            File fileInsert = new File(options.getAcessLog());

            fileLog.setAccessFile(options.getAcessLog());
            fileLog.setName(fileInsert.getName());

            logDAO.insertFile(fileLog);
            fileId = logDAO.getIdFileLog();

            System.out.println("=========== Opening File ================");
            FileInputStream fileInputStream = new FileInputStream(options.getAcessLog());
            BufferedReader file = new BufferedReader(new InputStreamReader(fileInputStream));

            int numLine = 0;
            System.out.println("------------ Registering logs in Database ----------------");

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

                log.setFileId(fileId);

                logDAO.insertLog(log);
                numLine++;
            }

            System.out.println(" ********* The logs were successfully recoded in database ********");
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        analyzeLog(options);
    }

    public void analyzeLog(Options options) {
        LocalDateTime startDate, endDate;
        int duration, fileId;

        fileId = logDAO.getIdFileLog();

        System.out.println("=========== Analyzing the logs ==============");
        if(options.getDuration().equals("hourly")) {
            duration = 1;
        } else {
            duration = 24;
        }

        startDate = options.getStartDate();
        endDate = startDate.plusHours(duration);

        List<IpBlocked> ipBlockeds = logDAO.getIpAccess(startDate, endDate, options.getThreshold(), fileId);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %30s %30s", "IP ADDRESS", "QUANTITY ACCESS", "REASON");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");

        for(IpBlocked ipBlocked : ipBlockeds) {
            ipBlocked.setReason("Blocked because for made more than " + options.getThreshold() + "requests per " + options.getDuration());
            ipBlocked.setFileId(fileId);
            logDAO.insertIpBlocked(ipBlocked);

            System.out.format("%10s %20s %40s", ipBlocked.getIpAddress(), ipBlocked.getQuantity(), ipBlocked.getReason());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }
}
