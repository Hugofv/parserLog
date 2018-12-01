package com.ef;

import com.ef.service.LogService;

import java.io.*;

public class Parser {

    public static void main(String[] args) {

        LogService logService = new LogService();

        System.out.println("Deu Certo");

        String[] partAcessLog = args[0].split("=");
        String acessLog = partAcessLog[1];

        String[] partStartDate = args[1].split("=");
        String startDate = partStartDate[1];

        String[] partDuration = args[2].split("=");
        String duration = partDuration[1];

        try {
            FileInputStream fileInputStream = new FileInputStream(acessLog);
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(fileInputStream));

            logService.readLog(bufferedInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
