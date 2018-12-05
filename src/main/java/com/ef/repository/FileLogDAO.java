package com.ef.repository;

import com.ef.model.FileLog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class of Data Access Object references {@link FileLog}
 *
 * @author Hugo Fernandes
 */
public class FileLogDAO {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    /**
     * Method responsible of save instance {@link FileLog} in database.
     *
     * @param fileLog
     * @throws SQLException
     */
    public void insertFile(FileLog fileLog) throws SQLException {
        String sql = "INSERT INTO LogFile(Name, AccessFile) VALUES (? , ?);";

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, fileLog.getName());
            preparedStatement.setString(2, fileLog.getAccessFile());

            preparedStatement.execute();

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

    /**
     * Method responsible in get last id of {@link FileLog} registered in database.
     *
     * @return
     * @throws SQLException
     */
    public int getIdFileLog() throws SQLException {
        String sql = "SELECT max(Id) AS Id FROM LogFile;";
        int id = 0;

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = Integer.parseInt(resultSet.getString("Id"));
            }

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

        return id;
    }
}
