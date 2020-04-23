package com.netcracker.ec.services.util;

import com.netcracker.ec.services.db.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IdGenerator {
    private int id;
    private static IdGenerator idGenerator = null;

    private IdGenerator(){
        generateId();
    }

    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public int nextId() {
        return id++;
    }

    private void generateId() {
        id = 1;
        try {
            String sqlQuery = "select getNcId();";

            ResultSet resultSet = DbWorker.getInstance().executeSelect(sqlQuery);
            resultSet.next();
            id = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
