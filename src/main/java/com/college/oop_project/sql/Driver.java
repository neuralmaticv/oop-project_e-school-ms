package com.college.oop_project.sql;

import java.sql.*;

public class Driver {
    private Connection conn = null;
    private final String url = "jdbc:mysql://localhost:3306/ors1_opp_2021_2022";
    private final String userName = "admin";
    private final String password = "Admin@dmin2022";

    public Driver() {
        startConnection();
    }

    public void startConnection() {
        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
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
