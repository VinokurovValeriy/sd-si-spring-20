package com.netcracker.ec.services.util;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.domain.enums.AttributeType;

import java.util.Scanner;

public class AttributeValueReader {

    private final Scanner scanner;

    public AttributeValueReader(Scanner scanner){
        this.scanner = scanner;
    }

    public String read(NcAttribute attr) {
        String value = null;
        Integer attrTypeId = attr.getAttributeTypeDef().getType();
        switch (AttributeType.getOperationById(attrTypeId)) {
            case TEXT:
                value = readText();
                break;
            case NUMBER:
                value = readNumber();
                break;
            case DECIMAL:
                value = readDecimal();
                break;
            case DATE:
                value = readDate();
                break;
            case REFERENCE:
                value = readReference();
                break;
            case LIST:
                value = readList();
                break;
                default:
                    break;
        }
        return value;
    }

    private String readText() {
        return scanner.next();
    }

    private String readNumber() {
        return String.valueOf(scanner.nextInt());
    }

    private String readDecimal() {
        return String.valueOf(scanner.nextDouble());
    }

    private String readDate() {
        return scanner.next();
    }

    private String readReference() {
        return scanner.next();
    }

    private String readList() {
        return scanner.next();
    }
}
