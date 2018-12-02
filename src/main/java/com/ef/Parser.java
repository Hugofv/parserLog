package com.ef;

import com.ef.ValueObject.Options;
import com.ef.service.LogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static void main(String[] args) {

        LogService logService = new LogService();
        Options options = new Options();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd.HH:mm:ss");

        if(args.length != 4) {
            System.out.println("The Option not informed, please verify command");
            throw new RuntimeException();
        }

        String[] partAcessLog = args[0].split("=");
        options.setAcessLog(partAcessLog[1]);

        String[] partStartDate = args[1].split("=");
        options.setStartDate(LocalDateTime.parse(partStartDate[1], formatter));

        String[] partDuration = args[2].split("=");
        options.setDuration(partDuration[1]);

        String[] partThrehold = args[3].split("=");
        options.setThreshold(Integer.parseInt(partThrehold[1]));

        logService.readLog(options);
    }
}
