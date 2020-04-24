- [Requirements](#requirements)
  - [Project Structure](#project-structure)
  - [Application Description](#application-description)
    - [Operation Exit](#operation-exit)
    - [Operation Create Order](#operation-create-order)
    - [Operation Show Orders](#operation-show-orders)
    - [Start Provisioning](#start-provisioning)
    - [Disconnect Order](#disconnect-order)
    - [Modify Order](#modify-order)
    - [Show statistics](#show-statistics)
  - [Assumptions](#assumptions)
    - [Id Generator](#id-generator)
    - [Technical Attributes](#technical-attributes)
    - [Order Name Generated](#order-name-generated)
  - [End-to-End Cases](#end-to-end-cases)
    - [Case 1: Create Order.](#case-1-create-order)
    - [Case 2: Show Orders.](#case-2-show-orders)

# Requirements

Создать консольное приложение на языке Java с использованием базы данных MySQL.

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
          |         |    └── AttributeType.java
          │         └── order
          │              └── Oder.java
          ├── provisioning
          │    └── operations
          │         ├── CreateOrderOperation.java
          │         └── Operation.java
          ├── services
          │    ├── console
          │    │    └── Console.java
          │    └── db
          │         ├── connection
          │         │    └── <DataBase>Connection.java
          │         ├── impl
          |         |    ├── ObjectServiceImpl.java
          │         │    └── NcObjectTypeServiceImpl.java
          │         ├── DbWorker.java
          |         ├── ObjectService.java
          │         └── NcObjectTypeService
          │         
          └── Application.java
```

- com.netcracker.ec.model.db: Сущности описывающие таблицы в БД.
  - Не нужно создавать классы для nc_params/nc_references/nc_attr_object_types таблиц. Эти таблицы используются только для хранения данных. 
  - Имена enum в единственном числе.
  - Не нужно создавать лишние прослойки классов в БД, которые не относятся к определённым сущностям.

## Application Description

Данное консольное приложение должно предоставлять меню с возможностью выполнения следующих операций:

### Operation Exit

Выйти из приложения

### Operation Create Order

При выборе этой опции в консоли должен выводиться список объектных типов ордеров. Пользователь должен иметь возможность выбора объектного типа посредством ввода в консоли соответствуюего ID. Случай, когда пользователем был введен некорректный ID должен быть отработан: в таком случае приложение должно повторно запрашивать выбор ID. При выборе объектного типа приложение должно итеративно выводить список доступных атрибутов, предлагая пользователю ввести в консоли значение каждого параметра. Необходимо принять во внимание, что работа с атрибутами различного типа имеет свою специфику:
- В случае текстового атрибута должна вставляться новая запись в таблицу nc_params, а введенное пользователем значение будет храниться в поле value данной таблицы.
- В случае листового атрибута в консоли пользователь будет вводить ID листового значения. Приложение должно проверять, что Type Definition данного атрибута содержит введенное пользователем ID листового значения. Новая запись должна вставляться в таблицу nc_params, а введенное пользователем ID листового значения будет храниться в поле list_value_id данной таблицы.
- В случае референсного атрибута приложение должно выводить в консоли список объектов (в формате: ID: Name) того объектного типа, на который ссылается Type Definition референсного атрибута. Пользователь должен иметь возможность путем ввода в консоли ID объекта из списка возможных выбрать, на какой объект должен ссылаться новосозданный ордер. Новая запись должна вставляться в таблицу nc_references, а введенное пользователем ID объекта будет храниться в поле reference данной таблицы.

**NOTE**: 
- В случае атрибута ‘Order Status’ системой должно автоматически проставляться значение ‘Entering’.
- В случае атрибута ‘Order Aim’ системой должно автоматически проставляться значение ‘New’.
- 
### Operation Show Orders

При выборе данной опции приложение должно предоставлять пользователю возможность вывода всех ордеров (Name) или возможность вывода ордеров определенного объектного типа. Во втором случае в консоли должен выводиться список доступных типов ордеров. Пользователь должен иметь возможность ввода определенного ID после чего приложением будет выдан отфильтрованный список ордеров.

### Start Provisioning

При выборе данной опции приложение должно выводить список ордеров со значением атрибута ‘Order Status’ = ‘Entering’. Пользователь должен иметь возможность выбора из этих ордеров определенного ордера путем ввода соответствующего ID. После этого значение атрибута ‘Order Status’ для данного ордера должно быть изменено на ‘Completed’.

### Disconnect Order

При выборе данной опции приложение должно выводить список ордеров со значением атрибута ‘Order Status’ = ‘Completed. Пользователь должен иметь возможность выбора из этих ордеров определенного ордера путем ввода соответствующего ID. После этого значение атрибута ‘Order Aim для данного ордера должно быть изменено на ‘Disconnect.

### Modify Order

При выборе данной опции приложение должно выводить список ордеров со значением атрибута ‘Order Status’ = ‘Completed. Пользователь должен иметь возможность выбора из этих ордеров определенного ордера путем ввода соответствующего ID. После этого приложение должно выводить список возможных модификаций ордера.
- Изменение типа подписки (Change Payment Type). При выборе данной опции должно автоматически происходить изменение типа подписки: Postpaid->Prepaid или Prepaid->Postpaid.
В случае выбора ордера с объектным типом ‘Video Order Object Type’ должна предоставляться опция:
- Изменение тарифного плана (Tariff Plan Change). При выборе данной опции должно автоматически происходить изменение тарифа: XDSL->GPON или GPON->XDSL.
В случае выбора ордера с объектным типом ‘Internet Order Object Type’ должна предоставляться опция:
- Изменение скорости подключения (Download Speed Change). Пользователь должен иметь возможность ввода нового значения скорости.
В случае выбора ордера с объектным типом ‘Mobile Order Object Type’ должна предоставляться опция:
- Изменение номера (Modify Phone Number). При выборе данной операции должен выводиться список доступных номеров (ID-Name). Пользователь должен иметь возможность выбора нового номера из списка доступных путем ввода соответствующего ID.
В каждом из данных случаев значение атрибута ‘Order Aim’ для выбранного ордера должно быть изменено на ‘Modify.

### Show statistics
При выборе данной опции приложение должно предоставлять пользователю статистику, используя группировку данных в соответствие с одной из следующих опций:
- объектный тип ордера.
- атрибут ‘Order Aim’.
- атрибут ‘Order Status’.

## Assumptions

### Id Generator

Create table **nc_id**, that will be store unique *Id* value. Then create function, that will be return *Id* (and increase value) from **nc_id**.

```
create table nc_id (id int(8));    <-- create table
insert into nc_id set id = 1;      <-- insert initial Id value

delimiter $$                       <-- change delimiter from ; to $$

create function getNcId()          <-- create function with name getNcId and w/o input values
returns int                        <-- function return int value
begin                              <-- function body
declare res int(8);                <-- declare variable as res with int type
update nc_id set id = id + 1;      <-- function have to increase Id value in nc_id table
select id into res from nc_id;     <-- set Id to declareted above variable
return res;                        <-- return declareted above variable
end$$                              <-- end function body

delimiter ;                        <-- change delimiter from $$ to ;

select getId();
```

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