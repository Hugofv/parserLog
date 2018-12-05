package com.ef.service;

import com.ef.ValueObject.Options;
import com.ef.model.FileLog;
import com.ef.model.IpBlocked;
import com.ef.model.Log;
import com.ef.repository.FileLogDAO;
import com.ef.repository.IpBlockedDAO;
import com.ef.repository.LogDAO;
import dnl.utils.text.table.TextTable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe of business reference of {@link Log}.
 *
 * @author Hugo Fernandes
 */
public class LogService {

    private LogDAO logDAO = new LogDAO();
    private FileLogDAO fileLogDAO = new FileLogDAO();
    private IpBlockedDAO ipBlockedDAO = new IpBlockedDAO();

    /**
     * Read log file and save instances of {@link Log} in database, the according of specifications of application.
     *
     * @param options
     */
    public void readLog(Options options) {

        String line;
        String[] partLine;
        int fileId;

        List<Log> logs = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        try {
            FileLog fileLog = new FileLog();
            File fileInsert = new File(options.getAcessLog());

            fileLog.setAccessFile(options.getAcessLog());
            fileLog.setName(fileInsert.getName());

            fileLogDAO.insertFile(fileLog);
            fileId = fileLogDAO.getIdFileLog();

            System.out.println("============ Opening File ============");
            FileInputStream fileInputStream = new FileInputStream(options.getAcessLog());
            BufferedReader file = new BufferedReader(new InputStreamReader(fileInputStream));

            System.out.println();
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("---- REGISTERING LOGS IN DATABASE -----");
            System.out.print("\r[");

            while ((line = file.readLine()) != null) {
                Log log = new Log();

                partLine = line.split("\\|");

                log.setStartDate(LocalDateTime.parse(partLine[0], formatter));
                log.setIpAddress(partLine[1].replaceAll("^\"|\"$", ""));
                log.setRequest(partLine[2].replaceAll("^\"|\"$", ""));
                log.setStatusCode(Integer.parseInt(partLine[3].replaceAll("^\"|\"$", "")));
                log.setUserAgent(partLine[4].replaceAll("^\"|\"$", ""));

                log.setFileId(fileId);

                logs.add(log);

                if(logs.size() > 1000) {
                    logDAO.bulkInsert(logs);
                    logs = new ArrayList<>();
                    System.out.print("=");
                }
            }

            if (logs.size() > 0) {
                logDAO.bulkInsert(logs);
                System.out.print("=");
            }
            System.out.print("]");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(" ********* The logs were successfully recoded in database ********");
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println();
            System.out.println();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        analyzeLog(options);
    }

    /**
     * Analyze the logs of file, the according os parameters informed.
     *
     * @param options
     */
    public void analyzeLog(Options options) {
        LocalDateTime startDate, endDate;
        int duration, fileId = 0;

        try {
            fileId = fileLogDAO.getIdFileLog();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("=========== Analyzing the logs ==============");
        if(options.getDuration().equals("hourly")) {
            duration = 1;
        } else {
            duration = 24;
        }

        startDate = options.getStartDate();
        endDate = startDate.plusHours(duration);

        List<IpBlocked> ipBlockeds = null;
        try {
            ipBlockeds = ipBlockedDAO.getIpAccess(startDate, endDate, options.getThreshold(), fileId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String[] columnNames = {"IP ADDRESS","QUANTITY ACCESS", "REASON"};
        Object[][] dataTable = new Object[ipBlockeds.size()][columnNames.length];

        int i = 0;
        for(IpBlocked ipBlocked : ipBlockeds) {
            ipBlocked.setReason("Blocked because for made more than " + options.getThreshold() + " requests per " + options.getDuration());
            ipBlocked.setFileId(fileId);
            try {
                ipBlockedDAO.insertIpBlocked(ipBlocked);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dataTable[i] = new Object[] {ipBlocked.getIpAddress(), ipBlocked.getQuantity(), ipBlocked.getReason()};
            i++;
        }

        TextTable textTable = new TextTable(columnNames, dataTable);
        textTable.printTable();
        System.out.println("______________________________________________________________________________________________");
        System.out.println();
    }
}
