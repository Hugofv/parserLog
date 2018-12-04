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
            System.out.println("The option not informed, please verify command");
            throw new RuntimeException();
        }

        for(String arg : args) {
            String[] partArgs = arg.split("=");
            String key = partArgs[0].replaceAll("--", "");

            switch (key) {
                case "startDate":
                    options.setStartDate(LocalDateTime.parse(partArgs[1], formatter));
                    break;

                case "accesslog":
                    options.setAcessLog(partArgs[1]);
                    break;

                case "duration":
                    options.setDuration(partArgs[1]);
                    break;

                case "threshold":
                    options.setThreshold(Integer.parseInt(partArgs[1]));
            }
        }

        System.out.println("======= Process initialized =========");

        logService.readLog(options);
        logService.analyzeLog(options);
    }
}
