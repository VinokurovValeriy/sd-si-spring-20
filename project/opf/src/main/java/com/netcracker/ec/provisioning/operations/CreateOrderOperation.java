package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.console.Console;
import com.netcracker.ec.services.db.impl.NcAttributeService;
import com.netcracker.ec.services.db.impl.NcObjectService;
import com.netcracker.ec.services.db.impl.NcObjectTypeService;
import com.netcracker.ec.services.db.impl.NcParamsService;
import com.netcracker.ec.services.util.IdGenerator;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcAttributeService ncAttributeService;
    private final NcObjectService ncObjectService;
    private final NcParamsService ncParamsService;

    private final Console console = Console.getInstance();

    public CreateOrderOperation() {
        this.ncAttributeService = new NcAttributeService();
        this.ncObjectService = new NcObjectService();
        this.ncParamsService = new NcParamsService();
        this.ncObjectTypeService = new NcObjectTypeService();
    }

    @Override
    public void execute() {
        System.out.println("Please Select Object Type.");

        Map<Integer, NcObjectType> orderObjectTypeMap = ncObjectTypeService.getOrderObjectTypes();
        orderObjectTypeMap.forEach((key, value) -> System.out.println(key + " - " + value.getName()));

        Integer objectTypeId = console.nextOperation(orderObjectTypeMap.keySet());
        NcObjectType selectedObjectType = orderObjectTypeMap.get(objectTypeId);

        List<NcAttribute> attributeList = ncAttributeService.getAttributesByOrderType(selectedObjectType);

        try {
            Order order = new Order(selectedObjectType);
            order.setId(IdGenerator.generateId());
            order.setName(generateOrderName(selectedObjectType));
            attributeList.forEach(attr -> order.getParameters()
                    .put(attr, console.getAttributeValue(attr)));

            if (console.getSaveDialogueAnswer()) {
                addOrder(order);
                addOrderParams(order);
                console.printOrderInfo(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addOrder(Order order) {
        ncObjectService.insertOrder(order);
    }

    private void addOrderParams(Order order) {
        ncParamsService.insertParams(order.getParameters(), order.getId());
    }

    private String generateOrderName(NcObjectType objectType) {
        Scanner scanner = new Scanner(objectType.getName());
        return String.join(" ", scanner.next(), scanner.next())
                + " #" + (ncObjectService.getOrderCountByOrderType(objectType.getId()) + 1);
    }
}
