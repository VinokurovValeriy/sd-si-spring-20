package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NcListValue {
    private Integer id;
    private String value;
    private NcAttributeTypeDef attributeTypeDef;

    public NcListValue() {
    }

    public NcListValue(Integer id, String value, NcAttributeTypeDef attributeTypeDef) {
        this.id = id;
        this.value = value;
        this.attributeTypeDef = attributeTypeDef;
    }
}
