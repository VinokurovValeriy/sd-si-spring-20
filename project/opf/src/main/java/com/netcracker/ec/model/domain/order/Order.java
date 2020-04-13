package com.netcracker.ec.model.domain.order;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.db.NcObject;
import com.netcracker.ec.model.db.NcObjectType;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Order extends NcObject {
    Set<NcAttribute> attributes;

    public Order(NcObjectType objectType) {
        super(objectType);
        this.attributes = new HashSet<>();
    }

    public Order(Integer id, String name, NcObjectType objectType) {
        super(id, name, objectType);
        this.attributes = new HashSet<>();
    }
}
