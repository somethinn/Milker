-- Drop tables if they exist
DROP TABLE IF EXISTS Orderdetail CASCADE;
DROP TABLE IF EXISTS Orders CASCADE;
DROP TABLE IF EXISTS Product CASCADE;
DROP TABLE IF EXISTS Categories CASCADE;
DROP TABLE IF EXISTS Users CASCADE;
DROP TABLE IF EXISTS Role CASCADE;

-- Create table: role
CREATE TABLE role (
    roleid VARCHAR(255) PRIMARY KEY,
    rolename VARCHAR(255) NOT NULL,
    description TEXT
);

-- Insert data into role
INSERT INTO role (roleid, rolename, description) VALUES
('role1', 'USER', 'Regular user role'),
('role2', 'ADMIN', 'Administrator role'),
('role3', 'MANAGER', 'Manager role'),
('role4', 'SELLER', 'Seller role'),
('role5', 'DELIVERER', 'Deliverer role');

-- Create table: users
CREATE TABLE users (
    customerid VARCHAR(255) PRIMARY KEY,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    phonenumber VARCHAR(20),
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roleid VARCHAR(255),
    CONSTRAINT fk_role FOREIGN KEY (roleid) REFERENCES role(roleid) ON DELETE SET NULL
);

-- Insert data into users
INSERT INTO users (customerid, firstname, lastname, email, phonenumber, username, password, roleid) VALUES
('cust1', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'johndoe', 'password123', 'role1'),
('cust2', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'janesmith', 'password123', 'role2'),
('cust3', 'Alice', 'Johnson', 'alice.j@example.com', '5551234567', 'alicej', 'password123', 'role3'),
('cust4', 'Bob', 'Williams', 'bob.w@example.com', '5559876543', 'bobw', 'password123', 'role4'),
('cust5', 'Charlie', 'Brown', 'charlie.b@example.com', '5554567890', 'charlieb', 'password123', 'role5'),
('cust6', 'David', 'Miller', 'david.m@example.com', '5556543210', 'davidm', 'password123', 'role1');

-- Create table: categories
CREATE TABLE categories (
    categoriesid VARCHAR(255) PRIMARY KEY,
    categoriesname VARCHAR(255) NOT NULL,
    description TEXT
);

-- Insert data into categories
INSERT INTO categories (categoriesid, categoriesname, description) VALUES
('cat1', 'Dairy', 'Dairy products like milk and cheese'),
('cat2', 'Flavored Milk', 'Flavored milk products'),
('cat3', 'Organic Milk', 'Organic milk products'),
('cat4', 'Non-Dairy Milk', 'Non-dairy milk alternatives'),
('cat5', 'Specialty Milk', 'Specialty milk products');

-- Create table: product
CREATE TABLE product (
    productid VARCHAR(255) PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    quantity INT DEFAULT 0,
    price DECIMAL(10, 2),
    description VARCHAR(255),
    categoriesid VARCHAR(255),
    status_description VARCHAR(255),
    image TEXT, -- Corrected to TEXT
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_category FOREIGN KEY (categoriesid) REFERENCES categories(categoriesid) ON DELETE SET NULL
);

-- Insert data into product
INSERT INTO product (productid, product_name, quantity, price, description, categoriesid, status_description, image) VALUES
('prod1', 'Whole Milk 1L', 10, 2.50, 'Full-fat whole milk, 1 liter', 'cat1', 'Available', 'whole_milk.jpg'),
('prod2', 'Chocolate Milk 500mL', 15, 1.80, 'Chocolate flavored milk, 500mL', 'cat2', 'Available', 'choco_milk.jpg'),
('prod3', 'Organic Skim Milk 1L', 8, 3.00, 'Organic skim milk, 1 liter', 'cat3', 'Available', 'organic_skim.jpg'),
('prod4', 'Almond Milk 1L', 12, 3.50, 'Unsweetened almond milk, 1 liter', 'cat4', 'Available', 'almond_milk.jpg'),
('prod5', 'Lactose-Free Milk 1L', 5, 2.80, 'Lactose-free whole milk, 1 liter', 'cat5', 'Available', 'lactose_free.jpg');

-- Create table: orders
CREATE TABLE orders (
    orderid VARCHAR(255) PRIMARY KEY,
    memberid VARCHAR(255),
    total DECIMAL(10, 2),
    shippingaddress TEXT,
    orderdate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(50),
    CONSTRAINT fk_user FOREIGN KEY (memberid) REFERENCES users(customerid) ON DELETE SET NULL
);

-- Insert data into orders
INSERT INTO orders (orderid, memberid, total, shippingaddress, orderdate, status) VALUES
('order1', 'cust1', 5.00, '123 Main St, City', '2025-03-15 10:00:00', 'Delivered'),
('order2', 'cust2', 3.60, '456 Oak Ave, Town', '2025-03-16 12:00:00', 'Shipped'),
('order3', 'cust3', 6.00, '789 Pine Rd, Village', '2025-03-17 09:00:00', 'Processing'),
('order4', 'cust4', 7.00, '101 Elm St, City', '2025-03-17 11:00:00', 'Delivered'),
('order5', 'cust5', 5.60, '202 Birch Ln, Town', '2025-03-17 14:00:00', 'Shipped');

-- Create table: orderdetail
CREATE TABLE orderdetail (
    orderdetailid VARCHAR(255) PRIMARY KEY,
    orderid VARCHAR(255),
    productid VARCHAR(255),
    quantity INT,
    price DECIMAL(10, 2),
    CONSTRAINT fk_order FOREIGN KEY (orderid) REFERENCES orders(orderid) ON DELETE CASCADE,
    CONSTRAINT fk_product FOREIGN KEY (productid) REFERENCES product(productid) ON DELETE SET NULL
);

-- Insert data into orderdetail
INSERT INTO orderdetail (orderdetailid, orderid, productid, quantity, price) VALUES
('od1', 'order1', 'prod1', 2, 2.50),
('od2', 'order2', 'prod2', 2, 1.80),
('od3', 'order3', 'prod3', 2, 3.00),
('od4', 'order4', 'prod4', 2, 3.50),
('od5', 'order5', 'prod5', 2, 2.80);