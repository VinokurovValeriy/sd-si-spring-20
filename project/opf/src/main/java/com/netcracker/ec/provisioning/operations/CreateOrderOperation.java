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

import java.util.*;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcAttributeService ncAttributeService;
    private final NcObjectService ncObjectService;
    private final NcParamsService ncParamsService;

    private final Console console = Console.getInstance();

    public CreateOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeService();
        this.ncAttributeService = new NcAttributeService();
        this.ncObjectService = new NcObjectService();
        this.ncParamsService = new NcParamsService();
    }

    @Override
    public void execute() {
        System.out.println("Please Select Object Type.");

        Map<Integer, NcObjectType> orderObjectTypeMap = ncObjectTypeService.getOrderObjectTypes();
        orderObjectTypeMap.forEach((key, value) -> System.out.println(key + " - " + value.getName()));

        Integer objectTypeId = console.getOrderTypeId(orderObjectTypeMap.keySet());
        NcObjectType selectedObjectType = orderObjectTypeMap.get(objectTypeId);

        List<NcAttribute> attributeList = ncAttributeService.getAttributesByOrderType(selectedObjectType);

        Order order = new Order(selectedObjectType);
        order.setId(IdGenerator
                .getInstance()
                .nextId(order));
        order.setName(generateOrderName(selectedObjectType));
        attributeList.forEach(attr -> order.getParameters()
                .put(attr, console.getAttributeValue(attr)));

        if (console.getSaveDialogueAnswer()) {
            addOrder(order);
            addOrderParams(order);
            console.printOrderInfo(order);
        } else {
            IdGenerator.getInstance().resetGeneratedId();
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
                + " #" + (ncObjectService.getOrderCountByOrderType(objectType.getId())+1);
    }
}
