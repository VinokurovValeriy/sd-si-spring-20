package com.netcracker.ec.services;

import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NcObjectService {
    private static final DbWorker dbWorker = DbWorker.getInstance();
    private Connection connection;

    public NcObjectService() {
        this.connection = dbWorker.getConnection();
    }


    public int getOrderCountByOrderType(int orderTypeId) {
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select count(*) " +
                            "from nc_objects " +
                            "where object_type_id = ?;", orderTypeId);

            ps.setInt(1, orderTypeId);

            //for debug
            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Integer getOrderIdByName(String name) {
        Integer objectId = null;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select object_id from nc_objects where name = ?;");

            ps.setString(1, name);

            //for debug
            System.out.println(ps);

            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                objectId = resultSet.getInt("object_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objectId;
    }

    public boolean insertOrder(Order order) {
        boolean flag = false;
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into nc_objects values(null, ?, ?, null);");
            ps.setString(1, order.getName());
            ps.setInt(2, order.getObjectType().getId());

            //for debug
            System.out.println(ps);

            //flag = ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
