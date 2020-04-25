create database ncHomeTask;
use ncHomeTask;
show tables;

create table nc_object_types(
	object_type_id int unsigned auto_increment primary key,
	name varchar(50) not null,
    parent_id int unsigned not null,
	description varchar(200));

create table nc_objects(
	object_id int unsigned auto_increment primary key,
	name varchar(50) not null,
    object_type_id int unsigned,
	description varchar(200),
		foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_attr_type_defs(
	attr_type_def_id int unsigned auto_increment primary key,
    type int not null,
	object_type_id int unsigned,
	description varchar(200),
		foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_attributes(
	attr_id int unsigned auto_increment primary key,
	name varchar(50) not null,
    attr_type_def_id int unsigned,
		foreign key (attr_type_def_id) references nc_attr_type_defs(attr_type_def_id));

create table nc_attr_object_types(
	attr_id int unsigned,
    object_type_id int unsigned,
		foreign key (attr_id) references nc_attributes(attr_id),
		foreign key (object_type_id) references nc_object_types(object_type_id));

create table nc_list_values(
	list_value_id int unsigned auto_increment primary key,
	value varchar(50) not null,
    attr_type_def_id int unsigned,
		foreign key (attr_type_def_id) references nc_attr_type_defs(attr_type_def_id));

create table nc_params(
	attr_id int unsigned not null,
	object_id int unsigned not null,
	list_value_id int unsigned,
	value varchar(50),
		foreign key (object_id) references nc_objects(object_id),
        foreign key (attr_id) references nc_attributes(attr_id),
        foreign key (list_value_id) references nc_list_values(list_value_id));

create table nc_references(
	attr_id int unsigned not null,
	object_id int unsigned not null,
	reference int unsigned not null,
		foreign key (object_id) references nc_objects(object_id),
        foreign key (attr_id) references nc_attributes(attr_id));

create table nc_id_keeper(
	id int unsigned not null);
insert into nc_id_keeper values(12);

insert into nc_object_types values(null, 'All', 0, 'Based Object Type');
insert into nc_object_types values(null, 'Abstract Order Object Type', 1, 'Abstract object type for all product orders');
insert into nc_object_types values(null, 'Abstract Instance Object Type', 1, 'Abstract object type for all product instances');
insert into nc_object_types values(null, 'Internet Order Object Type', 2, null);
insert into nc_object_types values(null, 'Video Order Object Type', 2, null);
insert into nc_object_types values(null, 'Mobile Order Object Type', 2, null);
insert into nc_object_types values(null, 'Internet Instance Object Type', 3, null);
insert into nc_object_types values(null, 'Video Instance Object Type', 3, null);
insert into nc_object_types values(null, 'Mobile Instance Object Type', 3, null);
insert into nc_object_types values(null, 'Phone Number', 1, 'Phone Number Оbject Type');

insert into nc_attr_type_defs values(null, 0, null, 'For any Text attribute');
insert into nc_attr_type_defs values(null, 2, null, 'For any Number attribute');
insert into nc_attr_type_defs values(null, 3, null, 'For any Decimal attribute');
insert into nc_attr_type_defs values(null, 4, null, 'For any Date attribute');
insert into nc_attr_type_defs values(null, 6, null, 'Access Type Values');
insert into nc_attr_type_defs values(null, 6, null, 'Service Type Values');
insert into nc_attr_type_defs values(null, 6, null, 'Order Status Values');
insert into nc_attr_type_defs values(null, 9, 10, 'For any Reference to Phone Number OT');
insert into nc_attr_type_defs values(null, 6, null, 'Order Aim Values');

insert into nc_attributes values(null, 'Due Date', 4);
insert into nc_attributes values(null, 'Phone Number', 8);
insert into nc_attributes values(null, 'Access Type', 5);
insert into nc_attributes values(null, 'Download Speed', 1);
insert into nc_attributes values(null, 'Service Type', 6);
insert into nc_attributes values(null, 'Suspend Reason', 1);
insert into nc_attributes values(null, 'Activation Period', 2);
insert into nc_attributes values(null, 'Product Price', 3);
insert into nc_attributes values(null, 'Order Status', 7);
insert into nc_attributes values(null, 'Order Aim', 9);

insert into nc_list_values values(null, 'XDSL', 5);
insert into nc_list_values values(null, 'GPON', 5);
insert into nc_list_values values(null, 'Postpaid', 6);
insert into nc_list_values values(null, 'Prepaid', 6);
insert into nc_list_values values(null, 'Entering', 7);
insert into nc_list_values values(null, 'Completed', 7);
insert into nc_list_values values(null, 'Cancelled', 7);
insert into nc_list_values values(null, 'Processing', 7);
insert into nc_list_values values(null, 'New', 9);
insert into nc_list_values values(null, 'Modify', 9);
insert into nc_list_values values(null, 'Disconnect', 9);

insert into nc_attr_object_types values(1, 2);
insert into nc_attr_object_types values(2, 6);
insert into nc_attr_object_types values(3, 4);
insert into nc_attr_object_types values(4, 4);
insert into nc_attr_object_types values(5, 6);
insert into nc_attr_object_types values(6, 5);
insert into nc_attr_object_types values(7, 2);
insert into nc_attr_object_types values(8, 2);
insert into nc_attr_object_types values(9, 2);