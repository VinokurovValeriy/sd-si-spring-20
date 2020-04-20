package com.netcracker.ec.services.db;

import com.netcracker.ec.services.db.connection.MySqlConnection;
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
            connection = new MySqlConnection().getConnection();
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

    public void executeInsert(String sqlQuery) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlQuery);

        //for debug
        System.out.println(ps);

        ps.execute();
    }

    public ResultSet executeSelect(String sqlQuery) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(sqlQuery);

        //for debug
        System.out.println(ps);

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
