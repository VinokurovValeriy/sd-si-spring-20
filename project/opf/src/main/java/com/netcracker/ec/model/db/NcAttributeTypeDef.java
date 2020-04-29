package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NcAttributeTypeDef {
    private Integer id;
    private NcObjectType objectType;
    private Integer type;

    public NcAttributeTypeDef(Integer id, NcObjectType objectType, Integer type) {
        this.id = id;
        this.objectType = objectType;
        this.type = type;
    }

    public NcAttributeTypeDef() {
    }
}
