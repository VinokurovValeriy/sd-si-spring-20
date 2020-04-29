package com.netcracker.ec.services.console;

import com.netcracker.ec.model.db.NcAttribute;
import com.netcracker.ec.model.domain.enums.Operations;
import com.netcracker.ec.model.domain.order.Order;
import com.netcracker.ec.provisioning.operations.CreateOrderOperation;
import com.netcracker.ec.provisioning.operations.Operation;
import com.netcracker.ec.provisioning.operations.ShowOrderOperation;
import com.netcracker.ec.services.db.dbworker.DbWorker;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

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
        System.out.println("  Operations:");
        Arrays.stream(Operations.values()).forEach(System.out::println);
        String operationId = scanner.next();
        if (operationId != null) {
            switch (Operations.getOperationById(operationId)) {
                case CREATE:
                    operation = new CreateOrderOperation();
                    break;
                case SHOW_ORDERS:
                    operation = new ShowOrderOperation();
                    break;
                case EXIT:
                    System.out.println("Exit from the program.");
                    Console.getInstance().close();
                    DbWorker.getInstance().close();
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Enter right operation");
            getNextOperation();
        }
        return operation;
    }

    public String getAttributeValue(NcAttribute attr) {
        System.out.print(attr.getName() + ": ");
        return scanner.next();
    }


    public void printOrderInfo(Order order) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order name: " + order.getName() + "\n");
        order.getParameters().forEach((key, value) ->
                stringBuilder.append("  ")
                        .append(key.getName())
                        .append(": ")
                        .append(value)
                        .append("\n"));
        System.out.println(stringBuilder.toString());
    }

    public Integer nextOperation(Set<Integer> operationsSet) {
        Integer id;
        do {
            id = console.scanner.nextInt();
        } while (!operationsSet.contains(id));
        return id;
    }

    public boolean getSaveDialogueAnswer() {
        System.out.println("Do you want to save order?Press 'Y' or 'N'");
        return scanner.next().equals("Y");
    }

    public void close() {
        scanner.close();
    }
}
