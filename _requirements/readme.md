- [Requirements](#requirements)
  - [Project Structure](#project-structure)
  - [Assumptions](#assumptions)
    - [Technical Attributes](#technical-attributes)
    - [Order Name Generated](#order-name-generated)
  - [End-to-End Cases](#end-to-end-cases)
    - [Case 1: Create Order.](#case-1-create-order)
    - [Case 2: Show Orders.](#case-2-show-orders)

# Requirements

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
          |         ├── enums
          |         |    └── AttributeTypes.java
          │         └── order
          │              └── Oder.java
          ├── provisioning
          │    └── operations
          │         ├── CreateOrderOperation.java
          │         └── Operation.java
          ├── services
          │    ├── Console.java
          │    └── db
          │         ├── <DataBase>Connection.java
          │         └── DbWorker.java
          └── Application.java
```

## Assumptions

### Technical Attributes

Technical Attributes will populated according to condition.
| Attribute | Condition |
| --- | ---
| **'Order Status'** | //TBD |

### Order Name Generated

'Order' name generate based on Id of Order in Data Base and its 'Object Type' name.

For example for the 'Video Order': _Video Order #6_.

## End-to-End Cases

### Case 1: Create Order.

```
 - Execute an Application.
 - Show the list of available 'Operations'.
 - Select the 'Operation': 
     - 'Create Order' operation:
          - Show the list of available Order 'Object Types'.
          - Select the Order 'Object Type'.
          - Get all not technical 'Attributes' that relate to Selected Order 'Object Type'.
          - Enter values for 'Attributes' one by one.
          - Suggest to Save the 'Order'.
               - In case 'Y'
                    - Save the 'Order' and its parameters.
                    - Show created 'Order' in the follow format:
               
                         Order Name: <name>
                              <attr1_name>: <attr_value>
                              ....
                              ....
                              <attrN_name>: <attr_value>
               
               - In case 'N'
                    - Return to 'Operations' menu.

```
### Case 2: Show Orders.

```
 - Execute an Application.
 - Show the list of available 'Operations'.
 - Select the 'Operation': 
     - 'Show Orders' operation:
          - Show the list of available 'Orders' with all Attributes in the follow format:
               
               Order1 Name: <name>
                    <attr1_name>: <attr_value>
                    ....
                    ....
                    <attrN_name>: <attr_value>
               ================================
               Order2 Name: <name>
                    <attr1_name>: <attr_value>
                    ....
                    ....
                    <attrN_name>: <attr_value>
               ================================
               ....
               ....
               ================================
               OrderN Name: <name>
                    <attr1_name>: <attr_value>
                    ....
                    ....
                    <attrN_name>: <attr_value>

```