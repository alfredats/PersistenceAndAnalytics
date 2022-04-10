DROP SCHEMA IF EXISTS day15_user;

CREATE SCHEMA day15_user;
USE day15_user;

CREATE TABLE logins (
	username varchar(32) not null,
	password varchar(256) not null,
	
	primary key(username)
);

GRANT ALL PRIVILEGES ON day15_user.* TO 'user'@'%';