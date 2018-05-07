package com.zaritsky;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDB {
    private static Connection connection;

    public SQLDB() throws SQLException {
        try {
            connect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:tickets.db");
        System.out.println("Подключено к БД");
    }

    public static void disconect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}