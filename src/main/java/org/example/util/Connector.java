package org.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connector {

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                GlobalVariables.DB_URL,
                GlobalVariables.LOGIN,
                GlobalVariables.PASSWORD);
    }
}