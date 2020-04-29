package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Operations {
    OPERATION("Id", "Name"),
    EXIT("0", "Exit"),
    CREATE("1", "Create Order"),
    SHOW_ORDERS("2", "Show Order");

    private String id;
    private String name;

    private static Map<String, Operations> operationsMap = new HashMap<>();

    static {
        Arrays.stream(Operations.values()).forEach(e -> operationsMap.put(e.getId(), e));
    }

    public static Operations getOperationById(String id) {
        return operationsMap.get(id);
    }

    @Override
    public String toString() {
        return id + "-" + name;
    }
}
