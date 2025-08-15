-- Insert categories
INSERT INTO categories (name)
VALUES ('Fruits'),
       ('Vegetables'),
       ('Dairy'),
       ('Bakery'),
       ('Beverages');

-- Insert products
INSERT INTO products (name, price, description, category_id)
VALUES ('Red Apples', 3.50, 'Fresh red apples, crisp and juicy, perfect for snacks and salads.', 1),
       ('Bananas', 1.20, 'Ripe yellow bananas, sweet and rich in potassium.', 1),
       ('Broccoli', 2.00, 'Fresh green broccoli, ideal for steaming or stir-frying.', 2),
       ('Carrots', 1.50, 'Organic carrots, crunchy and naturally sweet.', 2),
       ('Whole Milk', 4.00, '1-liter full cream milk, rich and creamy.', 3),
       ('Cheddar Cheese', 5.50, 'Aged cheddar cheese, perfect for sandwiches and cooking.', 3),
       ('Baguette', 2.50, 'Freshly baked French baguette with a crispy crust.', 4),
       ('Chocolate Croissant', 3.00, 'Flaky pastry filled with rich chocolate.', 4),
       ('Orange Juice', 3.75, 'Freshly squeezed orange juice, 100% natural.', 5),
       ('Green Tea', 2.80, 'Premium green tea bags for a refreshing cup anytime.', 5);
