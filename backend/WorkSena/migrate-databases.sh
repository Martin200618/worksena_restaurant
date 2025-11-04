#!/bin/bash

echo "========================================"
echo "WorkSena Database Migration Script"
echo "========================================"

echo ""
echo "Migrating MySQL database..."
echo "================================"
./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql -Dspring-boot.run.arguments="--spring.jpa.hibernate.ddl-auto=create"

echo ""
echo "Migration completed for MySQL!"
echo ""

echo "Migrating PostgreSQL database..."
echo "================================"
./mvnw spring-boot:run -Dspring-boot.run.profiles=postgresql -Dspring-boot.run.arguments="--spring.jpa.hibernate.ddl-auto=create"

echo ""
echo "Migration completed for PostgreSQL!"
echo ""

echo "Migrating SQL Server database..."
echo "================================"
./mvnw spring-boot:run -Dspring-boot.run.profiles=sqlserver -Dspring-boot.run.arguments="--spring.jpa.hibernate.ddl-auto=create"

echo ""
echo "Migration completed for SQL Server!"
echo ""

echo "========================================"
echo "All database migrations completed!"
echo "========================================"