package com.Trabajo.WorkSena.Infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private DataSource dataSource;

    @Override
    public void run(String... args) throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            // Check if data already exists
            if (hasData(conn)) {
                System.out.println("✅ Data already exists, skipping initialization");
                return;
            }

            System.out.println("🚀 Starting data initialization with JDBC...");
            initializeData(conn);
            System.out.println("🎉 Data initialization completed successfully!");

        } catch (Exception e) {
            System.out.println("⚠️  Error during data initialization: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean hasData(Connection conn) throws Exception {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM categories");
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return rs.getInt(1) > 0;
        }
    }

    private void initializeData(Connection conn) throws Exception {
        conn.setAutoCommit(false);

        try {
            // ===========================================
            // CATEGORIES
            // ===========================================
            String insertCategory = "INSERT INTO categories (name, description, display_order, is_active, created_at, updated_at) VALUES (?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertCategory)) {
                // Appetizers
                stmt.setString(1, "Appetizers");
                stmt.setString(2, "Starters and small plates");
                stmt.setInt(3, 1);
                stmt.setBoolean(4, true);
                stmt.executeUpdate();

                // Main Courses
                stmt.setString(1, "Main Courses");
                stmt.setString(2, "Principal dishes");
                stmt.setInt(3, 2);
                stmt.setBoolean(4, true);
                stmt.executeUpdate();

                // Desserts
                stmt.setString(1, "Desserts");
                stmt.setString(2, "Sweet treats and desserts");
                stmt.setInt(3, 3);
                stmt.setBoolean(4, true);
                stmt.executeUpdate();

                // Beverages
                stmt.setString(1, "Beverages");
                stmt.setString(2, "Drinks and refreshments");
                stmt.setInt(3, 4);
                stmt.setBoolean(4, true);
                stmt.executeUpdate();

                // Salads
                stmt.setString(1, "Salads");
                stmt.setString(2, "Fresh salads and healthy options");
                stmt.setInt(3, 5);
                stmt.setBoolean(4, true);
                stmt.executeUpdate();
            }
            System.out.println("✅ Categories created");

            // ===========================================
            // SUPPLIERS
            // ===========================================
            String insertSupplier = "INSERT INTO suppliers (name, contact_person, phone, email, address, city, country, supplier_category, payment_terms, tax_id, rating, is_active, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertSupplier)) {
                // Supplier 1
                stmt.setString(1, "Fresh Farms Co.");
                stmt.setString(2, "Maria Gonzalez");
                stmt.setString(3, "+57-300-123-4567");
                stmt.setString(4, "maria@freshfarms.com");
                stmt.setString(5, "Calle 10 #20-30");
                stmt.setString(6, "Bogota");
                stmt.setString(7, "Colombia");
                stmt.setString(8, "Produce");
                stmt.setString(9, "Net 30");
                stmt.setString(10, "901234567-8");
                stmt.setInt(11, 4);
                stmt.setBoolean(12, true);
                stmt.executeUpdate();

                // Supplier 2
                stmt.setString(1, "Meat Masters Ltd.");
                stmt.setString(2, "Carlos Rodriguez");
                stmt.setString(3, "+57-301-234-5678");
                stmt.setString(4, "carlos@meatmasters.com");
                stmt.setString(5, "Carrera 15 #45-67");
                stmt.setString(6, "Medellin");
                stmt.setString(7, "Colombia");
                stmt.setString(8, "Meat");
                stmt.setString(9, "Net 15");
                stmt.setString(10, "812345678-9");
                stmt.setInt(11, 5);
                stmt.setBoolean(12, true);
                stmt.executeUpdate();
            }
            System.out.println("✅ Suppliers created");

            // ===========================================
            // INVENTORY ITEMS
            // ===========================================
            String insertInventory = "INSERT INTO inventory_items (name, description, category, sku, unit, current_stock, minimum_stock, maximum_stock, unit_cost, location, status, supplier_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertInventory)) {
                // Item 1
                stmt.setString(1, "Tomatoes");
                stmt.setString(2, "Fresh red tomatoes");
                stmt.setString(3, "Vegetables");
                stmt.setString(4, "VEG-001");
                stmt.setString(5, "kg");
                stmt.setBigDecimal(6, java.math.BigDecimal.valueOf(50.00));
                stmt.setBigDecimal(7, java.math.BigDecimal.valueOf(10.00));
                stmt.setBigDecimal(8, java.math.BigDecimal.valueOf(100.00));
                stmt.setBigDecimal(9, java.math.BigDecimal.valueOf(2500.00));
                stmt.setString(10, "Refrigerator A");
                stmt.setString(11, "ACTIVE");
                stmt.setLong(12, 1L);
                stmt.executeUpdate();

                // Item 2
                stmt.setString(1, "Chicken Breast");
                stmt.setString(2, "Boneless chicken breast");
                stmt.setString(3, "Meat");
                stmt.setString(4, "MEA-001");
                stmt.setString(5, "kg");
                stmt.setBigDecimal(6, java.math.BigDecimal.valueOf(30.00));
                stmt.setBigDecimal(7, java.math.BigDecimal.valueOf(5.00));
                stmt.setBigDecimal(8, java.math.BigDecimal.valueOf(50.00));
                stmt.setBigDecimal(9, java.math.BigDecimal.valueOf(15000.00));
                stmt.setString(10, "Freezer B");
                stmt.setString(11, "ACTIVE");
                stmt.setLong(12, 2L);
                stmt.executeUpdate();
            }
            System.out.println("✅ Inventory items created");

            // ===========================================
            // MENU ITEMS
            // ===========================================
            String insertMenuItem = "INSERT INTO menu_items (name, description, price, preparation_time, is_available, category_id, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertMenuItem)) {
                // Menu Item 1 (Salad - category_id = 5)
                stmt.setString(1, "Caesar Salad");
                stmt.setString(2, "Fresh romaine lettuce with caesar dressing, croutons, and parmesan");
                stmt.setBigDecimal(3, java.math.BigDecimal.valueOf(18500.00));
                stmt.setInt(4, 10);
                stmt.setBoolean(5, true);
                stmt.setLong(6, 5L);
                stmt.executeUpdate();

                // Menu Item 2 (Main Course - category_id = 2)
                stmt.setString(1, "Grilled Chicken Breast");
                stmt.setString(2, "Herb-marinated chicken breast with roasted vegetables");
                stmt.setBigDecimal(3, java.math.BigDecimal.valueOf(28500.00));
                stmt.setInt(4, 20);
                stmt.setBoolean(5, true);
                stmt.setLong(6, 2L);
                stmt.executeUpdate();
            }
            System.out.println("✅ Menu items created");

            // ===========================================
            // DINING TABLES
            // ===========================================
            String insertTable = "INSERT INTO dining_tables (table_number, capacity, location, status, description, is_active, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertTable)) {
                // Table 1
                stmt.setString(1, "T001");
                stmt.setInt(2, 2);
                stmt.setString(3, "Window Side");
                stmt.setString(4, "AVAILABLE");
                stmt.setString(5, "Romantic table for two");
                stmt.setBoolean(6, true);
                stmt.executeUpdate();

                // Table 2
                stmt.setString(1, "T002");
                stmt.setInt(2, 4);
                stmt.setString(3, "Center Area");
                stmt.setString(4, "AVAILABLE");
                stmt.setString(5, "Family table for four");
                stmt.setBoolean(6, true);
                stmt.executeUpdate();
            }
            System.out.println("✅ Dining tables created");

            // ===========================================
            // ORDERS
            // ===========================================
            String insertOrder = "INSERT INTO orders (order_number, customer_name, customer_phone, table_id, total_amount, status, notes, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertOrder)) {
                // Order 1
                stmt.setString(1, "ORD-2024-001");
                stmt.setString(2, "Juan Perez");
                stmt.setString(3, "+57-300-111-2222");
                stmt.setLong(4, 1L);
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(47000.00));
                stmt.setString(6, "SERVED");
                stmt.setString(7, "Birthday celebration");
                stmt.executeUpdate();

                // Order 2
                stmt.setString(1, "ORD-2024-002");
                stmt.setString(2, "Maria Gomez");
                stmt.setString(3, "+57-301-222-3333");
                stmt.setLong(4, 2L);
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(65000.00));
                stmt.setString(6, "SERVED");
                stmt.setString(7, "Business lunch");
                stmt.executeUpdate();
            }
            System.out.println("✅ Orders created");

            // ===========================================
            // INVOICES
            // ===========================================
            String insertInvoice = "INSERT INTO invoices (invoice_number, order_id, customer_name, customer_phone, subtotal, tax_rate, tax_amount, total_amount, status, issued_at, due_date, table_number, notes, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY), ?, ?, NOW(), NOW())";

            try (PreparedStatement stmt = conn.prepareStatement(insertInvoice)) {
                // Invoice 1
                stmt.setString(1, "INV-2024-001");
                stmt.setLong(2, 1L);
                stmt.setString(3, "Juan Perez");
                stmt.setString(4, "+57-300-111-2222");
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(47000.00));
                stmt.setBigDecimal(6, java.math.BigDecimal.valueOf(0.08));
                stmt.setBigDecimal(7, java.math.BigDecimal.valueOf(3760.00));
                stmt.setBigDecimal(8, java.math.BigDecimal.valueOf(50760.00));
                stmt.setString(9, "PAID");
                stmt.setInt(10, 1);
                stmt.setString(11, "Birthday celebration invoice");
                stmt.executeUpdate();

                // Invoice 2
                stmt.setString(1, "INV-2024-002");
                stmt.setLong(2, 2L);
                stmt.setString(3, "Maria Gomez");
                stmt.setString(4, "+57-301-222-3333");
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(65000.00));
                stmt.setBigDecimal(6, java.math.BigDecimal.valueOf(0.08));
                stmt.setBigDecimal(7, java.math.BigDecimal.valueOf(5200.00));
                stmt.setBigDecimal(8, java.math.BigDecimal.valueOf(70200.00));
                stmt.setString(9, "PAID");
                stmt.setInt(10, 3);
                stmt.setString(11, "Business lunch invoice");
                stmt.executeUpdate();
            }
            System.out.println("✅ Invoices created");

            // ===========================================
            // INVOICE ITEMS
            // ===========================================
            String insertInvoiceItem = "INSERT INTO invoice_items (invoice_id, menu_item_name, quantity, unit_price, total_price, notes) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(insertInvoiceItem)) {
                // Invoice Item 1
                stmt.setLong(1, 1L);
                stmt.setString(2, "Caesar Salad");
                stmt.setInt(3, 1);
                stmt.setBigDecimal(4, java.math.BigDecimal.valueOf(18500.00));
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(18500.00));
                stmt.setString(6, "Extra dressing");
                stmt.executeUpdate();

                // Invoice Item 2
                stmt.setLong(1, 1L);
                stmt.setString(2, "Chocolate Cake");
                stmt.setInt(3, 1);
                stmt.setBigDecimal(4, java.math.BigDecimal.valueOf(12000.00));
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(12000.00));
                stmt.setString(6, "Happy Birthday message");
                stmt.executeUpdate();

                // Invoice Item 3
                stmt.setLong(1, 2L);
                stmt.setString(2, "Grilled Chicken Breast");
                stmt.setInt(3, 1);
                stmt.setBigDecimal(4, java.math.BigDecimal.valueOf(28500.00));
                stmt.setBigDecimal(5, java.math.BigDecimal.valueOf(28500.00));
                stmt.setString(6, "Medium rare");
                stmt.executeUpdate();
            }
            System.out.println("✅ Invoice items created");

            conn.commit();
            System.out.println("🎉 All data committed successfully!");

        } catch (Exception e) {
            conn.rollback();
            throw e;
        }
    }
}