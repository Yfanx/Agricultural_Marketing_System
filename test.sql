use Agricultural_Marketing_System;

DELETE FROM Customer_Information;
#农产品
INSERT INTO Agricultural_Information (AgriculturalName, AgriculturalType, AgriculturalIntroduction, Price)
VALUES ('Tomato', 'Vegetable', 'Fresh red tomatoes', 2.50),
       ('Potato', 'Vegetable', 'Organic potatoes', 1.20),
       ('Carrot', 'Vegetable', 'Crunchy carrots', 1.00),
       ('Apple', 'Fruit', 'Juicy apples', 3.00),
       ('Banana', 'Fruit', 'Sweet bananas', 1.50),
       ('Orange', 'Fruit', 'Citrus oranges', 2.00),
       ('Broccoli', 'Vegetable', 'Green broccoli', 1.80),
       ('Strawberry', 'Fruit', 'Fresh strawberries', 4.00),
       ('Lettuce', 'Vegetable', 'Crisp lettuce', 1.30),
       ('Peach', 'Fruit', 'Ripe peaches', 3.50),
       ('Cucumber', 'Vegetable', 'Cool cucumbers', 1.10),
       ('Bell Pepper', 'Vegetable', 'Colorful bell peppers', 2.20),
       ('Grapes', 'Fruit', 'Seedless grapes', 2.80),
       ('Watermelon', 'Fruit', 'Large watermelons', 5.00),
       ('Mango', 'Fruit', 'Tropical mangoes', 3.20),
       ('Pineapple', 'Fruit', 'Sweet pineapples', 2.50),
       ('Cherry', 'Fruit', 'Fresh cherries', 4.50),
       ('Kiwi', 'Fruit', 'Tangy kiwis', 2.30),
       ('Spinach', 'Vegetable', 'Nutritious spinach', 1.40),
       ('Blueberry', 'Fruit', 'Juicy blueberries', 3.50);

SELECT * FROM Agricultural_Information;

#供应商
INSERT INTO Supplier_Information (SupplierName, SupplierAddress, SupplierPhone)
VALUES ('Supplier1', '123 Main St, City, State, Zip', '1234567890'),
       ('Supplier2', '456 Elm St, City, State, Zip', '2345678901'),
       ('Supplier3', '789 Oak St, City, State, Zip', '3456789012'),
       ('Supplier4', '321 Pine St, City, State, Zip', '4567890123'),
       ('Supplier5', '654 Maple St, City, State, Zip', '5678901234');

#Provide
INSERT INTO Provide (AgriculturalID, SupplierID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 1),
       (7, 2),
       (8, 3),
       (9, 4),
       (10, 5),
       (11, 1),
       (12, 2),
       (13, 3),
       (14, 4),
       (15, 5),
       (16, 1),
       (17, 2),
       (18, 3),
       (19, 4),
       (20, 5);



