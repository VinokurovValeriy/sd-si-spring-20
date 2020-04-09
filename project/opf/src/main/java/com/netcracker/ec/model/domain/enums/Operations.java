package com.netcracker.ec.model.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Operations {
    OPERATION("Operation Id", "Operation Name");

    private String id;
    private String name;
}
