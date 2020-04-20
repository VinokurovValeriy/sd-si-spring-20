package com.netcracker.ec.model.db;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NcObject extends NcEntity {
    private NcObjectType objectType;

    public NcObject(NcObjectType objectType) {
        this.objectType = objectType;
    }

    public NcObject(Integer id, String name, NcObjectType objectType) {
        super(id, name);
        this.objectType = objectType;
    }
}
