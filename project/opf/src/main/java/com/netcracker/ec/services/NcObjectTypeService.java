package com.netcracker.ec.services;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NcObjectTypeService {
    private static final DbWorker dbWorker = DbWorker.getInstance();
    private Connection connection;

    public NcObjectTypeService() {
        this.connection = dbWorker.getConnection();
    }

    public List<NcObjectType> getOrderObjectTypes() {
        List<NcObjectType> objectTypes = new ArrayList<>();

        try {

            PreparedStatement ps = connection.prepareStatement("select * from nc_object_types where parent_id = 2;");
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                objectTypes.add(
                  new NcObjectType(
                          resultSet.getInt(1),
                          resultSet.getString(2),
                          resultSet.getInt(3)
                  )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectTypes;
    }

}
