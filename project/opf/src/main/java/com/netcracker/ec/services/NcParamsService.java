package com.netcracker.ec.services;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

public class NcParamsService {
    private static final DbWorker dbWorker = DbWorker.getInstance();
    private Connection connection;

    public NcParamsService() {
        this.connection = dbWorker.getConnection();
    }

    public void insertParams(Map<NcAttribute, String> attributesMap, Integer objectId) {
        attributesMap.forEach((attr, value) -> insertParam(attr, value, objectId));
    }

    private void insertParam(NcAttribute attr, String value, Integer objectId) {

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into nc_params values(?, ?, null, ?);");
            ps.setInt(1, attr.getId());
            ps.setInt(2, objectId);
            ps.setString(3, value);

            //for debug
            System.out.println(ps);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
