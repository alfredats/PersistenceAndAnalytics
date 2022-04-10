use day15_user;

INSERT INTO logins (
	username,
	password
) VALUES
	('johnDoe', SHA1('helloWorld')),
	('janeDoe', SHA1('coffee')),
	('hitMonLee',SHA1('kick')),
	('snorlax', SHA1('sleep')),
	('hitMonChan', SHA1('kick'));
	