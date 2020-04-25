package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum OperationType {
    EXIT("0", "Exit"),
    SHOW_ORDERS("1", "Show orders"),
    CREATE_ORDER("2", "Create order");

    private String id;
    private String name;
    private static Map<String, OperationType> operations = new HashMap<>();

    static {
        Arrays.stream(OperationType.values()).forEach(operation -> operations.put(operation.id, operation));
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }

    public static OperationType getOperationById(String id) {
        return operations.get(id);
    }
}
