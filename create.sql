CREATE DATABASE movietalk DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;

GRANT ALL ON movietalk.* TO 'webproject'@'localhost' IDENTIFIED BY 'test';

use movietalk;

CREATE TABLE users (
	id INT UNSIGNED AUTO_INCREMENT,
	userid VARCHAR(30) NOT NULL UNIQUE PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE,
	pwd VARCHAR(80) NOT NULL, 
	email VARCHAR(80) NOT NULL
);

CREATE TABLE movies ( 
	id INT UNSIGNED PRIMA1RY KEY AUTO_INCREMENT,
	title VARCHAR(100) NOT NULL UNIQUE,
	link VARCHAR(255) NOT NULL,
	image VARCHAR(255),
	subtitle VARCHAR(255) NOT NULL,
	pubdate VARCHAR(255) NOT NULL,
	director VARCHAR(100) NOT NULL,
	actor VARCHAR(255) NOT NULL,
	userrating INT NOT NULL
);

CREATE TABLE chats (
	id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
	movietitle VARCHAR(100) NOT NULL,
	title VARCHAR(100) NOT NULL UNIQUE,
	userid VARCHAR(30) NOT NULL,
	message VARCHAR(255) NOT NULL,
	`created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
