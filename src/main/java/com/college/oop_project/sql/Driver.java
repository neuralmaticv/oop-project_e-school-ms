package com.college.oop_project.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Driver {
    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/oop_project_db";
    private final String userName = "admin";
    private final String password = "Admin@dmin2022";

    public Driver() {
        startConnection();
    }

    public Connection getConn() {
        return conn;
    }

    public void startConnection() {
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void endConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
