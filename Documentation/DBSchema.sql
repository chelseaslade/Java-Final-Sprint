-- Java Final Sprint - Schema for Database Population
-- December 11, 2024
-- SD11 Group 14 (Chelsea Mayne, Allison Boone, Ashley Legge)

--Most data in actual program was inserted using functions written in Java to connect with database directly - these are examples of how the items and users in the program would be queried outside of the program.

--Create Users Table
CREATE TABLE Users (
    user_id serial NOT NULL PRIMARY KEY,
    username varchar(50),
    password text,
    email varchar(100),
    role varchar(10)
);

--Create Products Table
CREATE TABLE Products (
    product_id serial NOT NULL PRIMARY KEY,
    name varchar(100),
    price numeric(10,2),
    quantity int,
    seller_id int
);

--Insert Users
INSERT INTO Users (username, password, email, role)
VALUES
('seller', 'newseller123', 'newseller@email.com', 'seller'),
('buyer', 'buyer 123', 'newbuyer@email.com', 'buyer'),
('admin', 'admin123', 'newadmin@email.com', 'admin');

--Insert Products
INSERT INTO Products (name, price, quantity, seller_id)
VALUES
('Twix  Bar', 1.00, 15, 5),
('Bottle of Water', 1.99, 20, 5),
('Large Chips', 2.50, 10, 6);

