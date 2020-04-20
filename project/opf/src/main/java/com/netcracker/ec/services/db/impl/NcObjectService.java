package com.netcracker.ec.services.db.impl;

import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NcObjectService {
    private static final DbWorker dbWorker = DbWorker.getInstance();

    public NcObjectService() {
    }

    public int getOrderCountByOrderType(int orderTypeId) {
        int count = 0;
        try {
            String sqlQuery = String.format(
                    "select count(*) " +
                            "from nc_objects " +
                            "where object_type_id = %d;",
                    orderTypeId);

            ResultSet resultSet = dbWorker.executeSelect(sqlQuery);
            resultSet.next();
            count = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public Order insertOrder(Order order) {

        try {
            String sqlQuery = String.format("insert into nc_objects values(null, '%s', %d, null);",
                    order.getName(),
                    order.getObjectType().getId());
            dbWorker.executeInsert(sqlQuery);
            order.setId(getLastId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    private Integer getLastId() {
        Integer id = null;
        try {
            String sqlQuery = "select last_insert_id();";
            ResultSet resultSet = dbWorker.executeSelect(sqlQuery);
            resultSet.next();
            id = resultSet.getInt(1);
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
