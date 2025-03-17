-- Create the database (Render will handle this, but included for completeness)
CREATE DATABASE milker;

-- Connect to the database (optional in script; Render auto-configures)
\connect milker;

-- Role Table
CREATE TABLE "Role" (
    "RoleID" VARCHAR(255) PRIMARY KEY,
    "RoleName" VARCHAR(255) NOT NULL DEFAULT 'N/A'
);

-- Users Table
CREATE TABLE "Users" (
    "CustomerID" VARCHAR(255) PRIMARY KEY,
    "FirstName" VARCHAR(255),
    "LastName" VARCHAR(255),
    "Email" VARCHAR(255) UNIQUE NOT NULL,
    "PhoneNumber" TEXT,
    "UserName" VARCHAR(255) NOT NULL DEFAULT 'N/A',
    "Password" VARCHAR(255) NOT NULL,
    "RoleID" VARCHAR(255),
    CONSTRAINT fk_role FOREIGN KEY ("RoleID") REFERENCES "Role"("RoleID") ON DELETE SET NULL
);

-- Categories Table
CREATE TABLE "Categories" (
    "CategoriesID" VARCHAR(255) PRIMARY KEY,
    "BrandName" VARCHAR(255),
    "AgeRange" VARCHAR(255),
    "SubCategories" VARCHAR(255),
    "packageType" VARCHAR(255),
    "source" VARCHAR(255),
    "CreateDate" TIMESTAMP,
    "UpdateDate" TIMESTAMP
);

-- Product Table
CREATE TABLE "Product" (
    "ProductID" VARCHAR(255) PRIMARY KEY,
    "ProductName" VARCHAR(255),
    "quantity" INT DEFAULT 0,
    "price" DECIMAL(10, 2),
    "Description" VARCHAR(255),
    "CategoriesID" VARCHAR(255),
    "statusDescription" VARCHAR(255),
    "image" TEXT,
    "CreateDate" TIMESTAMP,
    "UpdateDate" TIMESTAMP,
    CONSTRAINT fk_category FOREIGN KEY ("CategoriesID") REFERENCES "Categories"("CategoriesID") ON DELETE SET NULL
);

-- Orders Table
CREATE TABLE "Orders" (
    "OrderID" VARCHAR(255) PRIMARY KEY,
    "MemberID" VARCHAR(255),
    "Total" DECIMAL(10, 2) NOT NULL,
    "ShippingAddress" VARCHAR(255),
    "OrderStatus" BOOLEAN DEFAULT FALSE,
    "CreateDate" TIMESTAMP,
    "UpdateDate" TIMESTAMP,
    CONSTRAINT fk_user FOREIGN KEY ("MemberID") REFERENCES "Users"("CustomerID") ON DELETE CASCADE
);

-- OrderDetail Table
CREATE TABLE "OrderDetail" (
    "ProductID" VARCHAR(255),
    "OrderID" VARCHAR(255),
    "quantity" INT DEFAULT 1,
    "RegistrationDate" TIMESTAMP,
    "CreateDate" TIMESTAMP,
    "UpdateDate" TIMESTAMP,
    PRIMARY KEY ("ProductID", "OrderID"),
    CONSTRAINT fk_product FOREIGN KEY ("ProductID") REFERENCES "Product"("ProductID") ON DELETE CASCADE,
    CONSTRAINT fk_order FOREIGN KEY ("OrderID") REFERENCES "Orders"("OrderID") ON DELETE CASCADE
);

-- Fake Data
-- Role (5 examples)
INSERT INTO "Role" ("RoleID", "RoleName") VALUES
('role1', 'USER'),
('role2', 'ADMIN'),
('role3', 'SELLER'),
('role4', 'GUEST'),
('role5', 'MANAGER');

-- Users (6 examples)
INSERT INTO "Users" ("CustomerID", "FirstName", "LastName", "Email", "PhoneNumber", "UserName", "Password", "RoleID") VALUES
('cust1', 'John', 'Doe', 'john.doe@example.com', '1234567890', 'johndoe', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role1'),
('cust2', 'Jane', 'Smith', 'jane.smith@example.com', '0987654321', 'janesmith', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role1'),
('cust3', 'Alice', 'Johnson', 'alice.j@example.com', '5551234567', 'alicej', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role2'),
('cust4', 'Bob', 'Brown', 'bob.brown@example.com', '4449876543', 'bobbrown', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role1'),
('cust5', 'Charlie', 'Davis', 'charlie.d@example.com', '3334567890', 'charlied', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role3'),
('cust6', 'Eve', 'Wilson', 'eve.wilson@example.com', '2226543210', 'evewilson', '$2a$10$XURP2ZqgN1U/v4W4LxKrqO9rwHQK9bLdUoA2fzcsLy2PaN31zR/a2', 'role1');

-- Categories (5 examples)
INSERT INTO "Categories" ("CategoriesID", "BrandName", "AgeRange", "SubCategories", "packageType", "source", "CreateDate", "UpdateDate") VALUES
('cat1', 'DairyFresh', 'All Ages', 'Whole Milk', 'Bottle', 'Cow', '2025-03-01 10:00:00', '2025-03-01 10:00:00'),
('cat2', 'PureMilk', 'Kids', 'Flavored Milk', 'Carton', 'Cow', '2025-03-02 12:00:00', '2025-03-02 12:00:00'),
('cat3', 'OrganicCo', 'Adults', 'Skim Milk', 'Bottle', 'Cow', '2025-03-03 14:00:00', '2025-03-03 14:00:00'),
('cat4', 'LactoFree', 'All Ages', 'Lactose-Free', 'Carton', 'Cow', '2025-03-04 16:00:00', '2025-03-04 16:00:00'),
('cat5', 'GoatMilkCo', 'All Ages', 'Goat Milk', 'Bottle', 'Goat', '2025-03-05 18:00:00', '2025-03-05 18:00:00');

-- Product (6 examples)
INSERT INTO "Product" ("ProductID", "ProductName", "quantity", "price", "Description", "CategoriesID", "statusDescription", "image", "CreateDate", "UpdateDate") VALUES
('prod1', 'Whole Milk 1L', 10, 2.50, 'Fresh whole milk', 'cat1', 'Available', 'milk1.jpg', '2025-03-01 10:00:00', '2025-03-01 10:00:00'),
('prod2', 'Chocolate Milk 500mL', 15, 1.80, 'Chocolate flavored milk', 'cat2', 'Available', 'choco.jpg', '2025-03-02 12:00:00', '2025-03-02 12:00:00'),
('prod3', 'Skim Milk 1L', 8, 2.20, 'Low-fat skim milk', 'cat3', 'Available', 'skim.jpg', '2025-03-03 14:00:00', '2025-03-03 14:00:00'),
('prod4', 'Lactose-Free Milk 1L', 12, 3.00, 'Lactose-free milk', 'cat4', 'Available', 'lactosefree.jpg', '2025-03-04 16:00:00', '2025-03-04 16:00:00'),
('prod5', 'Goat Milk 500mL', 5, 3.50, 'Pure goat milk', 'cat5', 'Available', 'goat.jpg', '2025-03-05 18:00:00', '2025-03-05 18:00:00'),
('prod6', 'Strawberry Milk 500mL', 20, 1.90, 'Strawberry flavored milk', 'cat2', 'Available', 'strawberry.jpg', '2025-03-06 20:00:00', '2025-03-06 20:00:00');

-- Orders (5 examples)
INSERT INTO "Orders" ("OrderID", "MemberID", "Total", "ShippingAddress", "OrderStatus", "CreateDate", "UpdateDate") VALUES
('order1', 'cust1', 5.00, '123 Milk St, City A', FALSE, '2025-03-10 09:00:00', '2025-03-10 09:00:00'),
('order2', 'cust2', 3.60, '456 Dairy Rd, City B', FALSE, '2025-03-11 10:00:00', '2025-03-11 10:00:00'),
('order3', 'cust3', 6.70, '789 Cream Ln, City C', FALSE, '2025-03-12 11:00:00', '2025-03-12 11:00:00'),
('order4', 'cust4', 2.20, '101 Milk Ave, City D', FALSE, '2025-03-13 12:00:00', '2025-03-13 12:00:00'),
('order5', 'cust5', 7.00, '202 Cheese St, City E', FALSE, '2025-03-14 13:00:00', '2025-03-14 13:00:00');

-- OrderDetail (6 examples)
INSERT INTO "OrderDetail" ("ProductID", "OrderID", "quantity", "RegistrationDate", "CreateDate", "UpdateDate") VALUES
('prod1', 'order1', 2, '2025-03-10 09:00:00', '2025-03-10 09:00:00', '2025-03-10 09:00:00'),
('prod2', 'order2', 2, '2025-03-11 10:00:00', '2025-03-11 10:00:00', '2025-03-11 10:00:00'),
('prod3', 'order3', 3, '2025-03-12 11:00:00', '2025-03-12 11:00:00', '2025-03-12 11:00:00'),
('prod4', 'order4', 1, '2025-03-13 12:00:00', '2025-03-13 12:00:00', '2025-03-13 12:00:00'),
('prod5', 'order5', 2, '2025-03-14 13:00:00', '2025-03-14 13:00:00', '2025-03-14 13:00:00'),
('prod6', 'order3', 1, '2025-03-12 11:00:00', '2025-03-12 11:00:00', '2025-03-12 11:00:00');