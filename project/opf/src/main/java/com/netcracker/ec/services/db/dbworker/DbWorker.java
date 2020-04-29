package com.netcracker.ec.services.db.dbworker;

import com.netcracker.ec.services.db.connection.MySQLConnection;
import lombok.Getter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Getter
public class DbWorker {
    private Connection connection;
    private static DbWorker dbWorker = null;

    private DbWorker() {
        try {
            this.connection = new MySQLConnection().getConnection();
        } catch (Exception e) {
            System.err.println("SQLException:\n" + e);
            throw new RuntimeException(e);
        }
    }

    public static DbWorker getInstance() {
        if (dbWorker == null) {
            dbWorker = new DbWorker();
        }
        return dbWorker;
    }

    public void executeInsert(String sqlRequest) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlRequest);
        ps.execute();
    }

    public ResultSet executeSelect(String sqlRequest) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlRequest);
        return ps.executeQuery();
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
