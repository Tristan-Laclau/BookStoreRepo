DROP DATABASE IF EXISTS Bookstore;
CREATE DATABASE Bookstore;
USE Bookstore;

CREATE TABLE t_clients (
	IdClient int(4) PRIMARY KEY AUTO_INCREMENT,
	firstname varchar(20) NOT NULL,
	lastname varchar(30) NOT NULL,
	email varchar(50) NOT NULL,
	password varchar(30) NOT NULL,
	address varchar(100)
) ENGINE = InnoDB;

INSERT INTO t_clients (firstname, lastname, email, password, address) VALUES ('Tristan', 'Laclau', 'tlaclau@mail.com', 'tl','Ondres');
INSERT INTO t_clients (firstname, lastname, email, password, address) VALUES ('Sebastien', 'Allaire', 'sallaire@mail.com', 'sa','St-Geours');
INSERT INTO t_clients (firstname, lastname, email, password, address) VALUES ('Eric', 'Mauler', 'emauler@mail.com','em','Dordogne');

CREATE TABLE t_books (
	IdBook int(4) PRIMARY KEY AUTO_INCREMENT,
	title varchar(100) NOT NULL,
	author varchar(50),
	description varchar(100),
	price float(8) NOT NULL DEFAULT 0,
	isUsed boolean NOT NULL DEFAULT FALSE
) ENGINE = InnoDB;

INSERT INTO t_books (title, author, description, price) VALUES ('The hitchhikers guide to the galaxy', 'Douglas Adams', 'placeholder text',15);
INSERT INTO t_books (title, author, description, price) VALUES ('The Stranger','Albert Camus','placeholder text',10 );

CREATE TABLE t_orders (
	IdOrder int(4) PRIMARY KEY AUTO_INCREMENT,
	IdClient int(4) NOT NULL,
	FOREIGN KEY (IdClient) REFERENCES t_clients(IdClient),
	Date varchar(8) NOT NULL,
	price float(8) NOT NULL DEFAULT 0
) ENGINE = InnoDB;

INSERT INTO t_orders (IdClient, Date, price) VALUES ( 1 , '27/04/22' , 10);
INSERT INTO t_orders (IdClient, Date, price) VALUES ( 2 , '27/04/22', 20);

CREATE TABLE t_themes (
	IdTheme int(4) PRIMARY KEY AUTO_INCREMENT,
	name varchar(20)
) ENGINE = InnoDB;

INSERT INTO t_themes (name) VALUES ('Science-Fiction');
INSERT INTO t_themes (name) VALUES ('Comedy');
INSERT INTO t_themes (name) VALUES ('French literature');

CREATE TABLE order_details (
	IdOrder int(4) NOT NULL,
	IdBook int(4) NOT NULL,
	 FOREIGN KEY (IdOrder) REFERENCES t_orders(IdOrder),
	 FOREIGN KEY (IdBook) REFERENCES t_books(IdBook),
	amount int(2)
) ENGINE = InnoDB;

CREATE TABLE book_details (
	IdTheme int(4) NOT NULL,
	IdBook int(4) NOT NULL,
	FOREIGN KEY (IdTheme) REFERENCES t_themes(IdTheme),
	FOREIGN KEY (IdBook) REFERENCES t_books(IdBook)
) ENGINE = InnoDB;

INSERT INTO book_details (IdTheme, IdBook) VALUES (1 , 1);
INSERT INTO book_details (IdTheme,IdBook) VALUES (2 , 1);
INSERT INTO book_details (IdTheme,IdBook) VALUES (3 , 2);
