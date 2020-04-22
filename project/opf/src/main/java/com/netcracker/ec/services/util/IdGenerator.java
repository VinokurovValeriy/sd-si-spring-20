package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcEntity;
import com.netcracker.ec.model.domain.enums.IdType;
import com.netcracker.ec.services.db.impl.NcObjectService;

public class IdGenerator {
    private int id;
    private String currentClassName;
    private static IdGenerator idGenerator = null;

    private IdGenerator(){
        this.id = 0;
        this.currentClassName = NcEntity.class.getSimpleName();
    }

    public static IdGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IdGenerator();
        }
        return idGenerator;
    }

    public void resetGeneratedId() {
        this.id = 0;
        this.currentClassName = NcEntity.class.getSimpleName();
    }

    public int nextId(NcEntity entity) {
        boolean checkClass = entity.getClass()
                .getSimpleName()
                .equals(currentClassName);

        if (checkClass) {
            return ++id;
        } else {
            return generateId(entity);
        }
    }

    private int generateId(NcEntity entity) {
        currentClassName = entity.getClass().getSimpleName();
        switch (IdType.getTypeByName(currentClassName)) {
            case OrderId:
                id = generateOrderId();
                break;
            case ObjectTypeId:
                break;
            case AttributeId:
                break;
            default:
                break;
        }
        return id;
    }

    private int generateOrderId() {
        return new NcObjectService().getOrderCount() + 1;
    }
}
