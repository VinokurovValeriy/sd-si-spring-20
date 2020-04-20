package com.netcracker.ec.services.console;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.domain.enums.OperationType;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.provisioning.operations.CreateOrderOperation;
import com.netcracker.ec.provisioning.operations.ExitOperation;
import com.netcracker.ec.provisioning.operations.Operation;
import com.netcracker.ec.provisioning.operations.ShowOrdersOperation;

import java.util.Arrays;
import java.util.Scanner;

public class Console {
    private final Scanner scanner;
    private static Console console = null;

    private Console() {
        this.scanner = new Scanner(System.in);
    }

    public static Console getInstance() {
        if (console == null) {
            console = new Console();
        }
        return console;
    }

    public Operation getNextOperation() {
        Operation operation = null;
        printAvailableOperations();
        String operationId = console.nextOperationCommand();

        switch (OperationType.getOperationById(operationId)) {
            case CREATE_ORDER:
                operation = new CreateOrderOperation();
                break;
            case SHOW_ORDERS:
                operation = new ShowOrdersOperation();
                break;
            case EXIT:
                operation = new ExitOperation();
                break;
            default:
                break;
        }
        return operation;
    }

    public String getAttributeValue(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return scanner.next();
    }

    public void printAvailableOperations() {
        System.out.println("  Operations:");
        Arrays.stream(OperationType.values()).forEach(System.out::println);
    }

    public void printOrderInfo(Order order) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Order name: " + order.getName() + "\n");
        order.getAttributes().forEach((key, value) ->
                stringBuffer.append("  ")
                        .append(key.getName())
                        .append(": ")
                        .append(value)
                        .append("\n"));
        System.out.println(stringBuffer.toString());
    }

    public String nextOperationCommand() {
        return scanner.next();
    }

    public Integer nextOperationId() {
        return scanner.nextInt();
    }

    public boolean getSaveDialogueAnswer() {
        System.out.println("Save order?[Y/N]");
        return scanner.next().equals("Y");
    }

    public void close() {
        System.out.println("Exit in process...");
        scanner.close();
    }
}
