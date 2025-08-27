package com.matrixmedicalnetwork.bdd.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = configLoader.getProperty("db.url");
        String user = configLoader.getProperty("db.username");
        String password = configLoader.getProperty("db.password");
        return DriverManager.getConnection(url, user, password);

    }
    public static Connection getConnectionMemberService() throws SQLException {
        String url = configLoader.getProperty("memberService.db.url");
        String user = configLoader.getProperty("memberService.db.username");
        String password = configLoader.getProperty("memberService.db.password");
        return DriverManager.getConnection(url, user, password);

    }
}