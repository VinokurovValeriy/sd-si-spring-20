package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum IdType {
    OrderId("Order"),
    ObjectTypeId("NcObjectType"),
    AttributeId("NcAttribute");

    private String name;
    private static Map<String, IdType> identifiers = new HashMap<>();

    static {
        Arrays.stream(IdType.values()).forEach(id -> identifiers.put(id.name, id));
    }

    public static IdType getTypeByName(String name) {
        return identifiers.get(name);
    }
}
