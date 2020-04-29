package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.db.dbworker.DbWorker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NcObjectTypeService {
    private static final DbWorker dbWorker = DbWorker.getInstance();

    public NcObjectTypeService() {

    }

    public Map<Integer, NcObjectType> getOrderObjectTypes() {
        Map<Integer, NcObjectType> objectTypes = new HashMap<>();

        try {
            String sqlQuery = "select * from nc_object_types where parent_id = 2;";
            ResultSet resultSet = dbWorker.executeSelect(sqlQuery);
            while (resultSet.next()) {
                objectTypes.put(resultSet.getInt(1),
                        new NcObjectType(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getInt(3)
                        )
                );
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectTypes;
    }

}
