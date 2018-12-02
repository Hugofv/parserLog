package com.ef.repository;

import com.ef.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class LogDAO {
    private Connection connection = ConnectionFactory.getConnection();

    public void insert(Log log) {
        String sql = "INSERT INTO LogRegistry(StartDate, IpAddress, Request, StatusCode, UserAgent) " +
                "VALUES (?, ?, ?, ?, ?)";

        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, log.getStartDate().toString());
            preparedStatement.setString(2, log.getIpAddress());
            preparedStatement.setString(3, log.getRequest());
            preparedStatement.setInt(4, log.getStatusCode());
            preparedStatement.setString(5, log.getUserAgent());

            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
