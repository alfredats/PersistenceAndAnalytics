DROP SCHEMA IF EXISTS kwikmart;

CREATE SCHEMA kwikmart;

USE kwikmart;

CREATE TABLE purchase_order(
	order_id char(8) not null,
	name varchar(64) not null,
	order_date date default (CURRENT_DATE),
	
	primary key(order_id)
);

CREATE TABLE sku(
	prod_id int not null auto_increment,
	description varchar(256) not null,
	unit_price float(5,2) not null,
	
	primary key(prod_id)
);


CREATE TABLE line_item(
	item_id int not null auto_increment,
	quantity int default '1',
	
	-- foreign keys
	order_id char(8),
	prod_id int,
	
	primary key(item_id),
	constraint fk_order_id
		foreign key(order_id)
		references purchase_order(order_id),
	constraint fk_prod_id
		foreign key(prod_id)
		references sku(prod_id)
);