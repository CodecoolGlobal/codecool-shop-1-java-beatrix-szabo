DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS admins;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS cart;

CREATE TABLE users (
	id serial PRIMARY KEY,
	username TEXT,
	email TEXT,
	user_password TEXT,
);

INSERT INTO users (username, email, user_password) VALUES
('TesztElek', 'asd@gmail.com', 'asd'),
('Joska', 'joska@gyerek.com', '123**asd');

CREATE TABLE admins (
	username TEXT,
	admin_password TEXT
);
/*THIS iS FINE*/
INSERT INTO admins(username, admin_password) VALUES
('Betti', 'asd'),
('Krisz','asd'),
('Roland', 'asd');

/*THIS iS FINE*/
CREATE TABLE Product (
	id serial PRIMARY KEY,
	product_name TEXT,
	description TEXT,
	price int,
	currency TEXT,
	supplier_id int,
	product_category_id int
);

INSERT INTO product(product_name, description, price, currency, supplier_id, product_category_id)
VALUES('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.',
       49.9, 'USD', 2, 1),
      ('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.',
       479, 'USD', 1, 2),
      ('Amazon Fire HD 8', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.',
       89, 'USD', 1, 1);

/*THIS iS FINE*/
CREATE TABLE product_category (
	id serial PRIMARY KEY,
    p_name text,
	department text,
	description text
);

INSERT INTO product_category (p_name, department,  description)
VALUES ('Tablet', 'Hardware',
        'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.'),
       ('Phone', 'Hardware', 'Phones');

/*THIS iS FINE*/
CREATE TABLE supplier (
	id serial PRIMARY KEY,
	s_name TEXT,
	description TEXT
);

INSERT INTO supplier (s_name, description)
VALUES ('Amazon', 'Digital content and services'),('Lenovo', 'Computers');

/* I think it wont be good */
CREATE TABLE cart (
	id serial PRIMARY KEY,
	product_id int
);







