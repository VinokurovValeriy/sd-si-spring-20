package com.netcracker.ec.model.db;

public class NcListValue {
    private Integer id;
    private String value;
    private NcAttributeTypeDef attributeTypeDef;

    public NcListValue(Integer id, String value, NcAttributeTypeDef attributeTypeDef) {
        this.id = id;
        this.value = value;
        this.attributeTypeDef = attributeTypeDef;
    }

    public NcListValue() {
    }
}
