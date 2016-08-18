package com.fred.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class AbstractDAO {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String HOST = "jdbc:mysql://mysql/java";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return getConnection().prepareStatement(sql);
    }
}
