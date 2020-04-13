package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NcAttributeTypeDef {
    private Integer id;
    private Integer type;
    private NcObjectType objectType;

    public NcAttributeTypeDef() {
    }


    public NcAttributeTypeDef(Integer id, Integer type, NcObjectType objectType) {
        this.id = id;
        this.type = type;
        this.objectType = objectType;
    }
}
