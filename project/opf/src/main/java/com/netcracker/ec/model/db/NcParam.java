package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NcParam {
    private NcObject ncObject;
    private NcAttribute ncAttribute;
    private String Value;

    public NcParam() {
    }

    public NcParam(NcObject ncObject, NcAttribute ncAttribute, String value) {
        this.ncObject = ncObject;
        this.ncAttribute = ncAttribute;
        Value = value;
    }
}
