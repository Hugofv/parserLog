package com.ef.repository;

import com.ef.model.FileLog;
import com.ef.model.IpBlocked;
import com.ef.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogDAO {
    private Connection connection = ConnectionFactory.getConnection();

    public void insertFile(FileLog fileLog) {
        String sql = "INSERT INTO LogFile(Name, AccessFile) VALUES (? , ?);";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fileLog.getName());
            preparedStatement.setString(2, fileLog.getAccessFile());

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getIdFileLog() {
        String sql = "SELECT max(Id) AS Id FROM LogFile;";
        int id = 0;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("Id"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return id;
    }

    public void insertLog(Log log) {
        String sql = "INSERT INTO LogRegistry(StartDate, IpAddress, Request, StatusCode, UserAgent, FileId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, log.getStartDate().toString());
            preparedStatement.setString(2, log.getIpAddress());
            preparedStatement.setString(3, log.getRequest());
            preparedStatement.setInt(4, log.getStatusCode());
            preparedStatement.setString(5, log.getUserAgent());
            preparedStatement.setInt(6, log.getFileId());

            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<IpBlocked> getIpAccess(LocalDateTime startDate, LocalDateTime endTime, int threshold, int fileId) {

        List<IpBlocked> ipBlockeds = new ArrayList<>();
        String sql = "SELECT  " +
                "  Quantity,  " +
                "  IpAddress  " +
                " FROM ( " +
                " SELECT " +
                "  COUNT(*) AS Quantity, " +
                "  IpAddress " +
                "  FROM LogRegistry " +
                "  WHERE StartDate BETWEEN ? AND ? " +
                "  GROUP BY IpAddress " +
                ") AS access " +
                "WHERE Quantity >= ? AND FileId = ?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, startDate.toString());
            preparedStatement.setString(2, endTime.toString());
            preparedStatement.setInt(3, threshold);
            preparedStatement.setInt(4, fileId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                IpBlocked ipBlocked = new IpBlocked();

                ipBlocked.setQuantity(Long.parseLong(resultSet.getString("Quantity")));
                ipBlocked.setIpAddress(resultSet.getString("IpAddress"));

                ipBlockeds.add(ipBlocked);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ipBlockeds;
    }

    public void insertIpBlocked(IpBlocked ipBlocked) {
        String sql = "INSERT INTO IpBlocked(IpAddress, Quantity, Reason, FileId) VALUES (? , ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, ipBlocked.getIpAddress());
            preparedStatement.setLong(2, ipBlocked.getQuantity());
            preparedStatement.setString(3, ipBlocked.getReason());
            preparedStatement.setInt(4, ipBlocked.getFileId());

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
