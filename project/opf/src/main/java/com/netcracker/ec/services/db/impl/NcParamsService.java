package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.SQLException;
import java.util.Map;

public class NcParamsService {
    private static final DbWorker dbWorker = DbWorker.getInstance();

    public NcParamsService() {
    }

    public void insertParams(Map<NcAttribute, String> attributesMap, Integer objectId) {
        attributesMap.forEach((attr, value) -> insertParam(attr, value, objectId));
    }

    private void insertParam(NcAttribute attr, String value, Integer objectId) {

        try {
            String sqlQuery = String.format("insert into nc_params values(%d, %d, null, '%s');",
                    attr.getId(),
                    objectId,
                    value);
            dbWorker.executeInsert(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
