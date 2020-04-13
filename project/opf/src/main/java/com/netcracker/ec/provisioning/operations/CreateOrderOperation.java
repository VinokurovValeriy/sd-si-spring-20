package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.model.db.NcParam;
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

        Map<Integer, NcObjectType> orderObjectTypes = ncObjectTypeService.getOrderObjectTypes();
        orderObjectTypes.forEach((key, value) -> System.out.println(key + " - " + value.getName()));

        Integer objectTypeId = console.nextOperationId();
        NcObjectType objectType = orderObjectTypes.get(objectTypeId);

        List<NcAttribute> attributeList = ncAttributeService.getAttributesByOrderType(objectType);

        Order order = new Order(objectType);
        order.setName(generateOrderName(objectType));
        attributeList.forEach(attr->order.getAttributes().add(attr));

        List<NcParam> paramList = getParamsList(attributeList, order);

        if (console.getSaveDialogueAnswer()) {
            addOrder(order);
            addParams(paramList, order);
        }
    }

    private void addOrder(Order order) {
        ncObjectService.insertOrder(order);
    }

    private List<NcParam> getParamsList(List<NcAttribute> attributeList, Order order) {
        List<NcParam> paramList = new ArrayList<>();
        attributeList.forEach(attr -> paramList
                .add(
                    new NcParam(
                            new NcObject(null, order.getName(), order.getObjectType()),
                            attr,
                            console.getAttributeValue(attr))));
        return paramList;
    }

    private void addParams(List<NcParam> paramList,Order order) {
        Integer objectId = ncObjectService.getOrderIdByName(order.getName());
        paramList.forEach(param->{
            //use 1 for tests
            param.getNcObject().setId(1);
            ncParamsService.insertParam(param);
        });
    }

    private String generateOrderName(NcObjectType objectType) {
        Scanner scanner = new Scanner(objectType.getName());
        return String.join(" ", scanner.next(), scanner.next())
                + " #" + (ncObjectService.getOrderCountByOrderType(objectType.getId())+1);
    }
}
