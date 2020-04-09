package com.netcracker.ec.provisioning.operations;

import com.netcracker.ec.model.db.NcObjectType;
import com.netcracker.ec.services.NcObjectTypeService;

import java.util.List;

public class CreateOrderOperation implements Operation {
    private final NcObjectTypeService ncObjectTypeService;

    public CreateOrderOperation() {
        this.ncObjectTypeService = new NcObjectTypeService();
    }

    @Override
    public void execute() {
        System.out.println("Please Select OT.");

        List<NcObjectType> orderObjectTypes = ncObjectTypeService.getOrderObjectTypes();

        orderObjectTypes.forEach(ncObjectType -> System.out.println(ncObjectType.getId() + " - " + ncObjectType.getName()));

    }
}
