package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum AttributeType {
    TEXT(0, "Text"),
    NUMBER(2, "Number"),
    DECIMAL(3, "Decimal"),
    DATE(4, "Date"),
    LIST(6, "List"),
    REFERENCE(9, "Reference");

    private Integer id;
    private String name;
    private static Map<Integer, AttributeType> attributes = new HashMap<>();

    static {
        Arrays.stream(AttributeType.values()).forEach(a -> attributes.put(a.id, a));
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public static AttributeType getOperationById(Integer id) {
        return attributes.get(id);
    }
}
