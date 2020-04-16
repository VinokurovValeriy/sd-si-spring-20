package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.services.*;

import java.util.*;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;
    private final NcAttributeService ncAttributeService;
    private final NcObjectService ncObjectService;
    private final NcParamsService ncParamsService;

    private Console console = Console.getInstance();

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

        Integer objectTypeId = console.nextOperationId();
        NcObjectType selectedObjectType = orderObjectTypeMap.get(objectTypeId);

        List<NcAttribute> attributeList = ncAttributeService.getAttributesByOrderType(selectedObjectType);

        Order order = new Order(selectedObjectType);
        order.setName(generateOrderName(selectedObjectType));
        attributeList.forEach(attr -> order.getAttributes()
                .put(attr, console.getAttributeValue(attr)));

        if (console.getSaveDialogueAnswer()) {
            addOrder(order);
            addOrderParams(order);
        }
    }

    private void addOrder(Order order) {
        ncObjectService.insertOrder(order);
    }

    private void addOrderParams(Order order) {
        ncParamsService.insertParams(order.getAttributes(), order.getId());
    }

    private String generateOrderName(NcObjectType objectType) {
        Scanner scanner = new Scanner(objectType.getName());
        return String.join(" ", scanner.next(), scanner.next())
                + " #" + (ncObjectService.getOrderCountByOrderType(objectType.getId())+1);
    }
}
