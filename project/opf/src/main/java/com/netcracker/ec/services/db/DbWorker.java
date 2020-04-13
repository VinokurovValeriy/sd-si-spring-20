package com.netcracker.ec.services.db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.SQLException;

@Getter
public class DbWorker {
    private Connection connection;
    private static DbWorker dbWorker = null;

    private DbWorker() {
        try {
            this.connection = new MySqlConnection().getConnection();
        } catch (Exception e) {
            System.out.println(1);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static DbWorker getInstance() {
        if (dbWorker == null) {
            dbWorker = new DbWorker();
        }
        return dbWorker;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
