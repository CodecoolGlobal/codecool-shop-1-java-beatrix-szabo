DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Admins;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS ProductCategory;
DROP TABLE IF EXISTS Supplier;
DROP TABLE IF EXISTS Cart;

CREATE TABLE Users (
	id serial PRIMARY KEY,
	username TEXT,
	email TEXT,
	user_password TEXT
);

INSERT INTO Users (username, email, user_password) VALUES
('TesztElek', 'asd@gmail.com', 'asd'),
('Joska', 'joska@gyerek.com', '123**asd');

CREATE TABLE Admins (
	username TEXT,
	admin_password TEXT
);

INSERT INTO Admins(username, admin_password) VALUES
('Betti', 'asd');

CREATE TABLE Product (
	id serial PRIMARY KEY,
	product_name TEXT,
	description TEXT,
	price int,
	currency TEXT,
	supplier_id int,
	product_category_id int
);

INSERT INTO Product (product_name, description,	price, currency, supplier_id , product_category_id) VALUES
('Név', 'Leírás', 1450, 'EUR', 1, 5);

CREATE TABLE ProductCategory (
	id serial PRIMARY KEY,
	department text
);

INSERT INTO ProductCategory (department) VALUES ('department');


CREATE TABLE Supplier (
	id serial PRIMARY KEY,
	supplier_name TEXT
);

INSERT INTO Supplier (supplier_name) VALUES ('Apple');

CREATE TABLE Cart (
	id serial PRIMARY KEY,
	product_id int,
	full_price int
);

INSERT INTO Cart (product_id, full_price) VALUES(1, 1234);






