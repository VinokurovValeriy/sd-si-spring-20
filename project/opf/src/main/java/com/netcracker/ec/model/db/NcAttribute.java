package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NcAttribute extends NcEntity {
    private NcAttributeTypeDef attributeTypeDef;

    public NcAttribute(NcAttributeTypeDef attributeTypeDef) {
        this.attributeTypeDef = attributeTypeDef;
    }

    public NcAttribute(Integer id, String name, NcAttributeTypeDef attributeTypeDef) {
        super(id, name);
        this.attributeTypeDef = attributeTypeDef;
    }
}
