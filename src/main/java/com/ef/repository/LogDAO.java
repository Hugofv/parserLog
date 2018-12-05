package com.ef.repository;

import com.ef.model.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Class of Data Access Object references {@link Log}
 *
 * @author Hugo Fernandes
 */
public class LogDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    /**
     * Method responsible in save instances of {@link Log} in database.
     *
     * @param logs
     * @throws SQLException
     */
    public void bulkInsert(List<Log> logs) throws SQLException {
        String sql = "INSERT INTO LogRegistry(StartDate, IpAddress, Request, StatusCode, UserAgent, FileId) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for(Log log: logs){
                preparedStatement.setString(1, log.getStartDate().toString());
                preparedStatement.setString(2, log.getIpAddress());
                preparedStatement.setString(3, log.getRequest());
                preparedStatement.setInt(4, log.getStatusCode());
                preparedStatement.setString(5, log.getUserAgent());
                preparedStatement.setInt(6, log.getFileId());

                preparedStatement.addBatch();
                preparedStatement.clearParameters();
            }

            preparedStatement.executeBatch();
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }
}
