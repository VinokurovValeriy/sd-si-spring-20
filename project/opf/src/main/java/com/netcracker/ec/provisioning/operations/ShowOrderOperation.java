package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.impl.NcObjectService;
import com.netcracker.ec.services.db.impl.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcParamsService;

import java.util.List;

public class ShowOrderOperation implements Operation {

    private final NcObjectTypeService ncObjectTypeService;
    private final NcObjectService ncObjectService;
    private final NcParamsService ncParamsService;

    private final Console console = Console.getInstance();

    public ShowOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeService();
        this.ncObjectService = new NcObjectService();
        this.ncParamsService = new NcParamsService();
    }

    @Override
    public void execute() {
        showAllOrders();
    }

    private void showAllOrders() {
        List<Order> orders = ncObjectService.getOrders();

        orders.forEach(order -> order.setParameters(
                ncParamsService.getParamsByObjectId(order.getId())));

        orders.forEach(console::printOrderInfo);
    }
}
