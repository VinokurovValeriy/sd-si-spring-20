package com.netcracker.ec;

import com.netcracker.ec.model.domain.enums.Operations;
import com.netcracker.ec.provisioning.operations.CreateOrderOperation;
import com.netcracker.ec.provisioning.operations.Operation;
import com.netcracker.ec.services.db.DbWorker;


import java.util.Arrays;
import java.util.Scanner;

public class Application {
    public void run() throws Exception {
        Arrays.stream(Operations.values()).forEach(System.out::println);
        String operationId = scanner.next();
        Operation operation;
        switch (Operations.byId(operationId)) {
            case CREATE:
                operation = new CreateOrderOperation();
                break;
            case SHOW_ORDERS:
                break;
            default:
                break;
        }
        operation.execute();
    }
}
