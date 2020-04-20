package com.netcracker.ec.model.db;

import lombok.*;

@Getter
@Setter
public class NcEntity {
    private Integer id;
    private String name;

    public NcEntity() {
    }

    public NcEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
