package com.netcracker.ec.services;

import com.netcracker.ec.model.db.NcParam;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.db.DbWorker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NcParamsService {
    private static final DbWorker dbWorker = DbWorker.getInstance();
    private Connection connection;

    public NcParamsService() {
        this.connection = dbWorker.getConnection();
    }

    public boolean insertParam(NcParam param) {
        boolean flag = false;

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "insert into nc_params values(?, ?, null, ?);");
            ps.setInt(1, param.getNcAttribute().getId());
            ps.setInt(2, param.getNcObject().getId());
            ps.setString(3, param.getValue());

            //for debug
            System.out.println(ps);

            //flag = ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
