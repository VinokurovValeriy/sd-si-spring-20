package com.netcracker.ec.services.util;

import com.netcracker.ec.services.db.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class IdGenerator {

    private IdGenerator() {

    }

    public static int generateId() throws SQLException {
        String sqlQuery = "select getNcId();";
        ResultSet resultSet = DbWorker.getInstance().executeSelect(sqlQuery);
        resultSet.next();
        int id = resultSet.getInt(1);
        resultSet.close();
        return id;
    }
}
