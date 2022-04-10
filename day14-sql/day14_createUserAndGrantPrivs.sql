CREATE USER 'fred'@'%'
	IDENTIFIED BY 'pass';

GRANT ALL PRIVILEGES ON kwikmart.* TO 'fred'@'%';