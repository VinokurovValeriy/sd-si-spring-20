package com.netcracker.ec.services.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnection {
    private String url;

    public MySQLConnection() {
        this.url = "jdbc:mysql://localhost:3306/DbForNc?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    }

    public Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, "root", "5341961");
    }
}
