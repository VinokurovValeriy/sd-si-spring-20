package com.netcracker.ec.services.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connection {
    private String url;

    public Connection() {
        this.url = "jdbc:databasename:url";
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, "user", "password");
    }
}
