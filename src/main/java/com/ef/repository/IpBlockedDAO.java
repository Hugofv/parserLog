package com.ef.repository;

import com.ef.model.IpBlocked;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class of Data Access Object references {@link IpBlocked}
 *
 * @author Hugo Fernandes
 */
public class IpBlockedDAO {

    private Connection connection = null;
    private PreparedStatement preparedStatement = null;

    /**
     * Method responsible in get the {@link IpBlocked} through parameters informed.
     *
     * @param startDate
     * @param endTime
     * @param threshold
     * @param fileId
     * @return
     * @throws SQLException
     */
    public List<IpBlocked> getIpAccess(LocalDateTime startDate, LocalDateTime endTime, int threshold, int fileId) throws SQLException {

        List<IpBlocked> ipBlockeds = new ArrayList<>();
        String sql = "SELECT  " +
                "  Quantity,  " +
                "  IpAddress  " +
                " FROM ( " +
                " SELECT " +
                "  COUNT(*) AS Quantity, " +
                "  IpAddress, " +
                " FileId " +
                "  FROM LogRegistry " +
                "  WHERE StartDate BETWEEN ? AND ? " +
                "  GROUP BY IpAddress, FileId " +
                ") AS access " +
                "WHERE Quantity >= ? AND FileId = ?";

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

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
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return ipBlockeds;
    }

    /**
     * Method responsible in save instance of {@link IpBlocked} in database.
     *
     * @param ipBlocked
     * @throws SQLException
     */
    public void insertIpBlocked(IpBlocked ipBlocked) throws SQLException {
        String sql = "INSERT INTO IpBlocked(IpAddress, Quantity, Reason, FileId) VALUES (? , ?, ?, ?)";

        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, ipBlocked.getIpAddress());
            preparedStatement.setLong(2, ipBlocked.getQuantity());
            preparedStatement.setString(3, ipBlocked.getReason());
            preparedStatement.setInt(4, ipBlocked.getFileId());

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
}
