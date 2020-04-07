# Requirements

## Content
- [Project Structure](#project-structure)
- [End-to-End Cases](#end-to-end-cases)
    - [Case 1: Create Order.](#case-2-create-order)
    - [Case 2: Show Orders.](#case-2-show-orders)

## Project Structure

```
com
└── netcracker
     └── ec
          ├── model
          │    ├── db
          │    │    ├── NcEntity.java
          │    │    ├── NcObject.java
          │    │    └── NcObjectType.java
          │    └── domain
          │         └── Oder.java
          ├── provisioning
          │    └── operations
          │         ├── CreateOrderOperation.java
          │         └── Operation.java
          └── services
               ├── Console.java
               └── DbWorker.java
```

## End-to-End Cases

### Case 1: Create Order.

```
 - Execute an Application.
 - Show the list of available 'Operations'.
 - Select the 'Operation': 
     - 'Create Order' operation:
          - Show the list of available 'Object Types'.
          - Select the 'Object Type'.
          - Get all 'Attributes' that relate to Selected 'Object Type'.
          - Enter values for 'Attributes' one by one.
          - Save 'Order'.
          - Show created 'Order' in the follow format:
               
               Order Name: <name>
                    <attr1_name>: <attr_value>
                    ....
                    ....
                    <attrN_name>: <attr_value>

```
### Case 2: Show Orders.

```
 - Execute an Application.
 - Show the list of available 'Operations'.
 - Select the 'Operation': 
     - 'Show Orders' operation:
          - Show the list of available 'Orders'.

```