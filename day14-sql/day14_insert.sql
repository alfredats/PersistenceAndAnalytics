SELECT "INSERT INTO purchase_order";
INSERT INTO kwikmart.purchase_order (
	order_id,
	name
) values 
	('abcd1234','fred');
	

SELECT "INSERT INTO sku";
INSERT INTO kwikmart.sku (
	description,
	unit_price 
) VALUES 
	('coffee', 5.00);

SELECT * from kwikmart.sku;

-- this should pass
INSERT INTO kwikmart.line_item (
	order_id,
	prod_id
) VALUES 
	('abcd1234', 2);
	
-- this should fail
INSERT INTO kwikmart.line_item (
	order_id,
	prod_id 	
) VALUES 
	('hello', 999);