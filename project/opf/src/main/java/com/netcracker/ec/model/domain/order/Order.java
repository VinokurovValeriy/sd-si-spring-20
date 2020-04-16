package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Order extends NcObject {
    Map<NcAttribute, String> attributes;

    public Order(NcObjectType objectType) {
        super(objectType);
        this.attributes = new HashMap<>();
    }

    public Order(Integer id, String name, NcObjectType objectType) {
        super(id, name, objectType);
        this.attributes = new HashMap<>();
    }
}
