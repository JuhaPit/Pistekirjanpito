CREATE TABLE webuser (
	id SMALLINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(255) NOT NULL UNIQUE KEY,
	password_hash VARCHAR(255) NOT NULL,
	salt VARCHAR(255) NOT NULL,
	demo VARCHAR(255),
	pisteet VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE webuser
ADD pisteet VARCHAR(255);
