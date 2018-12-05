package com.ef.repository;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class ConnectionFactory.
 *
 * @author Hugo Fernandes
 */
public class ConnectionFactory {

    /**
     * Method responsible of provide connection with database.
     *
     * @return instance of connection
     */
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/test_log?useTimezone=true&serverTimezone=UTC";
            String username = "root";
            String password = "toor";

            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
