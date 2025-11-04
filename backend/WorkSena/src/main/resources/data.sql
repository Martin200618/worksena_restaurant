-- ===========================================
-- WORK SENA RESTAURANT - INITIAL DATA
-- ===========================================
-- This file contains initial data for all tables
-- Compatible with MySQL, PostgreSQL, and SQL Server

-- ===========================================
-- CATEGORIES
-- ===========================================
INSERT INTO categories (name, description, display_order, is_active, created_at, updated_at) VALUES
('Appetizers', 'Starters and small plates', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Main Courses', 'Principal dishes', 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Desserts', 'Sweet treats and desserts', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Beverages', 'Drinks and refreshments', 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Salads', 'Fresh salads and healthy options', 5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- SUPPLIERS
-- ===========================================
INSERT INTO suppliers (name, contact_person, phone, email, address, city, country, supplier_category, payment_terms, tax_id, rating, is_active, created_at, updated_at) VALUES
('Fresh Farms Co.', 'Maria Gonzalez', '+57-300-123-4567', 'maria@freshfarms.com', 'Calle 10 #20-30', 'Bogota', 'Colombia', 'Produce', 'Net 30', '901234567-8', 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Meat Masters Ltd.', 'Carlos Rodriguez', '+57-301-234-5678', 'carlos@meatmasters.com', 'Carrera 15 #45-67', 'Medellin', 'Colombia', 'Meat', 'Net 15', '812345678-9', 5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Dairy Delights Inc.', 'Ana Martinez', '+57-302-345-6789', 'ana@dairydelights.com', 'Avenida 68 #12-34', 'Cali', 'Colombia', 'Dairy', 'Net 30', '723456789-0', 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bakery Best', 'Luis Sanchez', '+57-303-456-7890', 'luis@bakerybest.com', 'Calle 72 #8-90', 'Barranquilla', 'Colombia', 'Bakery', 'Net 45', '634567890-1', 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Beverage Bros', 'Sofia Ramirez', '+57-304-567-8901', 'sofia@beveragebros.com', 'Carrera 7 #23-45', 'Cartagena', 'Colombia', 'Beverages', 'Net 30', '545678901-2', 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- INVENTORY ITEMS
-- ===========================================
INSERT INTO inventory_items (name, description, category, sku, unit, current_stock, minimum_stock, maximum_stock, unit_cost, location, status, supplier_id, created_at, updated_at) VALUES
('Tomatoes', 'Fresh red tomatoes', 'Vegetables', 'VEG-001', 'kg', 50.00, 10.00, 100.00, 2500.00, 'Refrigerator A', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chicken Breast', 'Boneless chicken breast', 'Meat', 'MEA-001', 'kg', 30.00, 5.00, 50.00, 15000.00, 'Freezer B', 'ACTIVE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Milk', 'Fresh whole milk', 'Dairy', 'DAI-001', 'liter', 20.00, 5.00, 40.00, 3500.00, 'Refrigerator C', 'ACTIVE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bread', 'White bread loaves', 'Bakery', 'BAK-001', 'unit', 25.00, 5.00, 50.00, 2000.00, 'Pantry A', 'ACTIVE', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Coca Cola', '2-liter bottles', 'Beverages', 'BEV-001', 'unit', 15.00, 3.00, 30.00, 4500.00, 'Storage B', 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Lettuce', 'Fresh romaine lettuce', 'Vegetables', 'VEG-002', 'kg', 15.00, 3.00, 25.00, 1800.00, 'Refrigerator A', 'ACTIVE', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Beef Ribeye', 'Premium ribeye steak', 'Meat', 'MEA-002', 'kg', 12.00, 2.00, 20.00, 35000.00, 'Freezer B', 'ACTIVE', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cheese', 'Cheddar cheese blocks', 'Dairy', 'DAI-002', 'kg', 8.00, 2.00, 15.00, 12000.00, 'Refrigerator C', 'ACTIVE', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Croissants', 'Butter croissants', 'Bakery', 'BAK-002', 'unit', 20.00, 4.00, 40.00, 1500.00, 'Pantry A', 'ACTIVE', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Orange Juice', 'Fresh squeezed juice', 'Beverages', 'BEV-002', 'liter', 10.00, 2.00, 20.00, 6000.00, 'Refrigerator D', 'ACTIVE', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- MENU ITEMS
-- ===========================================
INSERT INTO menu_items (name, description, price, preparation_time, is_available, category_id, created_at, updated_at) VALUES
('Caesar Salad', 'Fresh romaine lettuce with caesar dressing, croutons, and parmesan', 18500.00, 10, 1, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Grilled Chicken Breast', 'Herb-marinated chicken breast with roasted vegetables', 28500.00, 20, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Beef Ribeye Steak', '8oz ribeye steak with garlic mashed potatoes', 45000.00, 25, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chocolate Cake', 'Rich chocolate cake with vanilla frosting', 12000.00, 5, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fresh Orange Juice', 'Freshly squeezed orange juice', 8000.00, 3, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tomato Soup', 'Creamy tomato soup with basil garnish', 15000.00, 15, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Fish and Chips', 'Beer-battered cod with french fries', 32000.00, 18, 1, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Tiramisu', 'Classic Italian dessert with coffee and mascarpone', 14000.00, 5, 1, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Coca Cola', 'Ice cold Coca Cola', 5000.00, 1, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Garlic Bread', 'Toasted bread with garlic butter', 10000.00, 8, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- PRODUCT_CATEGORY (Many-to-Many relationship between menu_items and categories)
-- ===========================================
INSERT INTO product_category (menu_item_id, category_id, is_primary, created_at, updated_at) VALUES
(1, 5, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Caesar Salad -> Salads
(2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Grilled Chicken -> Main Courses
(3, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Beef Ribeye -> Main Courses
(4, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Chocolate Cake -> Desserts
(5, 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Orange Juice -> Beverages
(6, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Tomato Soup -> Appetizers
(7, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Fish and Chips -> Main Courses
(8, 3, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Tiramisu -> Desserts
(9, 4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), -- Coca Cola -> Beverages
(10, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); -- Garlic Bread -> Appetizers

-- ===========================================
-- DINING TABLES
-- ===========================================
INSERT INTO dining_tables (table_number, capacity, location, status, description, is_active, created_at, updated_at) VALUES
('T001', 2, 'Window Side', 'AVAILABLE', 'Romantic table for two', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T002', 2, 'Window Side', 'AVAILABLE', 'Table for two near window', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T003', 4, 'Center Area', 'AVAILABLE', 'Family table for four', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T004', 4, 'Center Area', 'AVAILABLE', 'Spacious table for four', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T005', 6, 'Private Area', 'AVAILABLE', 'Large table for groups', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T006', 6, 'Private Area', 'AVAILABLE', 'Group dining table', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T007', 8, 'VIP Area', 'AVAILABLE', 'Premium table for special occasions', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T008', 2, 'Bar Area', 'AVAILABLE', 'Bar counter seating', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T009', 2, 'Bar Area', 'AVAILABLE', 'Bar counter seating', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('T010', 4, 'Terrace', 'AVAILABLE', 'Outdoor terrace seating', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- CLIENTS
-- ===========================================
INSERT INTO clients (first_name, last_name, identification_type, identification_number, phone, email, loyalty_points, total_orders, total_spent, is_active, created_at, updated_at) VALUES
('Juan', 'Perez', 'CC', '12345678', '+57-300-111-2222', 'juan.perez@email.com', 150, 12, 245000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Maria', 'Gomez', 'CC', '87654321', '+57-301-222-3333', 'maria.gomez@email.com', 200, 18, 320000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Carlos', 'Rodriguez', 'CC', '11223344', '+57-302-333-4444', 'carlos.rodriguez@email.com', 75, 6, 120000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ana', 'Martinez', 'CC', '44332211', '+57-303-444-5555', 'ana.martinez@email.com', 300, 25, 450000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Luis', 'Sanchez', 'CC', '55667788', '+57-304-555-6666', 'luis.sanchez@email.com', 50, 4, 85000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sofia', 'Ramirez', 'CC', '88776655', '+57-305-666-7777', 'sofia.ramirez@email.com', 125, 10, 180000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Diego', 'Torres', 'CC', '99887766', '+57-306-777-8888', 'diego.torres@email.com', 90, 8, 145000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Camila', 'Herrera', 'CC', '66778899', '+57-307-888-9999', 'camila.herrera@email.com', 175, 15, 275000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Andres', 'Moreno', 'CC', '33445566', '+57-308-999-0000', 'andres.moreno@email.com', 60, 5, 95000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Valentina', 'Jimenez', 'CC', '77889900', '+57-309-000-1111', 'valentina.jimenez@email.com', 220, 20, 380000.00, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- CLIENT ADDRESSES
-- ===========================================
INSERT INTO client_address (client_id, address, city, state, postal_code, country, is_default, created_at, updated_at) VALUES
(1, 'Calle 10 #20-30', 'Bogota', 'Cundinamarca', '110111', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Carrera 15 #45-67', 'Medellin', 'Antioquia', '050001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Avenida 68 #12-34', 'Cali', 'Valle del Cauca', '760001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Calle 72 #8-90', 'Barranquilla', 'Atlantico', '080001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Carrera 7 #23-45', 'Cartagena', 'Bolivar', '130001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Calle 5 #10-15', 'Pereira', 'Risaralda', '660001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Carrera 9 #25-30', 'Manizales', 'Caldas', '170001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Avenida 3 #5-10', 'Ibague', 'Tolima', '730001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Calle 15 #8-12', 'Neiva', 'Huila', '410001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Carrera 12 #18-25', 'Villavicencio', 'Meta', '500001', 'Colombia', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- ORDERS
-- ===========================================
INSERT INTO orders (order_number, customer_name, customer_phone, table_id, total_amount, status, notes, created_at, updated_at) VALUES
('ORD-2024-001', 'Juan Perez', '+57-300-111-2222', 1, 47000.00, 'COMPLETED', 'Birthday celebration', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-002', 'Maria Gomez', '+57-301-222-3333', 3, 65000.00, 'COMPLETED', 'Business lunch', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-003', 'Carlos Rodriguez', '+57-302-333-4444', 2, 28500.00, 'COMPLETED', 'Quick lunch', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-004', 'Ana Martinez', '+57-303-444-5555', 5, 89000.00, 'COMPLETED', 'Family dinner', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-005', 'Luis Sanchez', '+57-304-555-6666', 4, 32000.00, 'COMPLETED', 'Date night', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-006', 'Sofia Ramirez', '+57-305-666-7777', 8, 15000.00, 'COMPLETED', 'Bar snack', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-007', 'Diego Torres', '+57-306-777-8888', 7, 120000.00, 'COMPLETED', 'Special occasion', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-008', 'Camila Herrera', '+57-307-888-9999', 6, 58000.00, 'COMPLETED', 'Girls night out', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-009', 'Andres Moreno', '+57-308-999-0000', 9, 24000.00, 'COMPLETED', 'After work drink', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('ORD-2024-010', 'Valentina Jimenez', '+57-309-000-1111', 10, 76000.00, 'COMPLETED', 'Outdoor dining', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- ORDER MENU ITEMS
-- ===========================================
INSERT INTO order_menu_items (order_id, menu_item_id, quantity, unit_price, total_price, menu_item_name, notes, created_at, updated_at) VALUES
(1, 1, 1, 18500.00, 18500.00, 'Caesar Salad', 'Extra dressing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 4, 1, 12000.00, 12000.00, 'Chocolate Cake', 'Happy Birthday message', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 5, 2, 8000.00, 8250.00, 'Fresh Orange Juice', 'One with extra pulp', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 2, 1, 28500.00, 28500.00, 'Grilled Chicken Breast', 'Medium rare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 6, 1, 15000.00, 15000.00, 'Tomato Soup', 'No cream', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 8, 1, 14000.00, 14000.00, 'Tiramisu', 'To share', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 9, 2, 5000.00, 8000.00, 'Coca Cola', 'One diet', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 2, 1, 28500.00, 28500.00, 'Grilled Chicken Breast', 'Well done', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 3, 1, 45000.00, 45000.00, 'Beef Ribeye Steak', 'Rare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 1, 2, 18500.00, 22000.00, 'Caesar Salad', 'One without croutons', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 4, 1, 12000.00, 12000.00, 'Chocolate Cake', 'Extra chocolate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 5, 2, 8000.00, 10000.00, 'Fresh Orange Juice', 'Fresh squeezed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 7, 1, 32000.00, 32000.00, 'Fish and Chips', 'Extra tartar sauce', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 10, 1, 10000.00, 10000.00, 'Garlic Bread', 'Extra garlic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 9, 1, 5000.00, 5000.00, 'Coca Cola', 'Ice cold', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 3, 2, 45000.00, 60000.00, 'Beef Ribeye Steak', 'One rare, one medium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 8, 2, 14000.00, 20000.00, 'Tiramisu', 'For dessert', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 5, 4, 8000.00, 20000.00, 'Fresh Orange Juice', 'Fresh batch', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 7, 1, 32000.00, 32000.00, 'Fish and Chips', 'Light batter', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 1, 1, 18500.00, 18500.00, 'Caesar Salad', 'Dressing on side', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 9, 2, 5000.00, 7500.00, 'Coca Cola', 'Extra ice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 10, 1, 10000.00, 10000.00, 'Garlic Bread', 'Toasted', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 9, 1, 5000.00, 5000.00, 'Coca Cola', 'No ice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 6, 1, 15000.00, 9000.00, 'Tomato Soup', 'Small portion', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 2, 1, 28500.00, 28500.00, 'Grilled Chicken Breast', 'Grilled', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 1, 1, 18500.00, 18500.00, 'Caesar Salad', 'Fresh greens', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 4, 1, 12000.00, 12000.00, 'Chocolate Cake', 'Rich chocolate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 5, 2, 8000.00, 11000.00, 'Fresh Orange Juice', 'Extra fresh', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- INVOICES
-- ===========================================
INSERT INTO invoices (invoice_number, order_id, customer_name, customer_phone, subtotal, tax_rate, tax_amount, total_amount, status, issued_at, due_date, table_number, notes, created_at, updated_at) VALUES
('INV-2024-001', 1, 'Juan Perez', '+57-300-111-2222', 47000.00, 0.08, 3760.00, 50760.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 1, 'Birthday celebration invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-002', 2, 'Maria Gomez', '+57-301-222-3333', 65000.00, 0.08, 5200.00, 70200.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 3, 'Business lunch invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-003', 3, 'Carlos Rodriguez', '+57-302-333-4444', 28500.00, 0.08, 2280.00, 30780.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 2, 'Quick lunch invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-004', 4, 'Ana Martinez', '+57-303-444-5555', 89000.00, 0.08, 7120.00, 96120.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 5, 'Family dinner invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-005', 5, 'Luis Sanchez', '+57-304-555-6666', 32000.00, 0.08, 2560.00, 34560.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 4, 'Date night invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-006', 6, 'Sofia Ramirez', '+57-305-666-7777', 15000.00, 0.08, 1200.00, 16200.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 8, 'Bar snack invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-007', 7, 'Diego Torres', '+57-306-777-8888', 120000.00, 0.08, 9600.00, 129600.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 7, 'Special occasion invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-008', 8, 'Camila Herrera', '+57-307-888-9999', 58000.00, 0.08, 4640.00, 62640.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 6, 'Girls night out invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-009', 9, 'Andres Moreno', '+57-308-999-0000', 24000.00, 0.08, 1920.00, 25920.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 9, 'After work drink invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('INV-2024-010', 10, 'Valentina Jimenez', '+57-309-000-1111', 76000.00, 0.08, 6080.00, 82080.00, 'PAID', CURRENT_TIMESTAMP, DATEADD(day, 30, CURRENT_TIMESTAMP), 10, 'Outdoor dining invoice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- INVOICE ITEMS
-- ===========================================
INSERT INTO invoice_items (invoice_id, menu_item_name, quantity, unit_price, total_price, notes, created_at, updated_at) VALUES
(1, 'Caesar Salad', 1, 18500.00, 18500.00, 'Extra dressing', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Chocolate Cake', 1, 12000.00, 12000.00, 'Happy Birthday message', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(1, 'Fresh Orange Juice', 2, 8000.00, 8250.00, 'One with extra pulp', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Grilled Chicken Breast', 1, 28500.00, 28500.00, 'Medium rare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Tomato Soup', 1, 15000.00, 15000.00, 'No cream', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Tiramisu', 1, 14000.00, 14000.00, 'To share', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(2, 'Coca Cola', 2, 5000.00, 8000.00, 'One diet', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(3, 'Grilled Chicken Breast', 1, 28500.00, 28500.00, 'Well done', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Beef Ribeye Steak', 1, 45000.00, 45000.00, 'Rare', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Caesar Salad', 2, 18500.00, 22000.00, 'One without croutons', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Chocolate Cake', 1, 12000.00, 12000.00, 'Extra chocolate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(4, 'Fresh Orange Juice', 2, 8000.00, 10000.00, 'Fresh squeezed', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(5, 'Fish and Chips', 1, 32000.00, 32000.00, 'Extra tartar sauce', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Garlic Bread', 1, 10000.00, 10000.00, 'Extra garlic', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(6, 'Coca Cola', 1, 5000.00, 5000.00, 'Ice cold', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Beef Ribeye Steak', 2, 45000.00, 60000.00, 'One rare, one medium', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Tiramisu', 2, 14000.00, 20000.00, 'For dessert', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(7, 'Fresh Orange Juice', 4, 8000.00, 20000.00, 'Fresh batch', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Fish and Chips', 1, 32000.00, 32000.00, 'Light batter', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Caesar Salad', 1, 18500.00, 18500.00, 'Dressing on side', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(8, 'Coca Cola', 2, 5000.00, 7500.00, 'Extra ice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Garlic Bread', 1, 10000.00, 10000.00, 'Toasted', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Coca Cola', 1, 5000.00, 5000.00, 'No ice', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(9, 'Tomato Soup', 1, 15000.00, 9000.00, 'Small portion', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Grilled Chicken Breast', 1, 28500.00, 28500.00, 'Grilled', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Caesar Salad', 1, 18500.00, 18500.00, 'Fresh greens', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Chocolate Cake', 1, 12000.00, 12000.00, 'Rich chocolate', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
(10, 'Fresh Orange Juice', 2, 8000.00, 11000.00, 'Extra fresh', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- ===========================================
-- SUCCESS MESSAGE
-- ===========================================
-- Initial data loaded successfully!
-- Total records inserted:
-- - Categories: 5
-- - Suppliers: 5
-- - Inventory Items: 10
-- - Menu Items: 10
-- - Product Categories: 10
-- - Dining Tables: 10
-- - Clients: 10
-- - Client Addresses: 10
-- - Orders: 10
-- - Order Menu Items: 28
-- - Invoices: 10
-- - Invoice Items: 28
--
-- Total: 136 records across all tables