package com.netcracker.ec.services.db;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Getter
public class DbWorker {
    private Connection connection;
    private static DbWorker dbWorker = null;

    private DbWorker() {
        try {
            this.connection = new Connection().getConnection();
        } catch (Exception e) {
            log.error.println("SQLException:\n" + e);
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
            log.error.println("SQLException:\n" + e);
        }
    }
}
