package com.netcracker.ec.services.util;

import com.netcracker.ec.services.db.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {

    public static int generateId() {
        int id = 1;
        try {
            String sqlQuery = "select getNcId();";

            ResultSet resultSet = DbWorker.getInstance().executeSelect(sqlQuery);
            resultSet.next();
            id = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
