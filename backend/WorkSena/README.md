# 🍽️ WorkSena Restaurant Management System

Sistema completo de gestión para restaurantes desarrollado con Spring Boot.

## 📋 Características

- **Gestión de Menús**: Items del menú, categorías y precios
- **Sistema de Pedidos**: Creación, seguimiento y estados de pedidos
- **Gestión de Mesas**: Control de ocupación y asignación
- **Inventario**: Control de stock y alertas automáticas
- **Proveedores**: Gestión de compras y proveedores
- **Facturación**: Generación automática de facturas con IVA
- **Reportes**: Estadísticas y análisis de ventas
- **APIs RESTful**: Documentadas con Swagger/OpenAPI

## 🚀 Inicio Rápido

### Prerrequisitos

- **Docker y Docker Compose**
- **Java 17+**
- **Maven 3.6+**

### 1. Clonar el repositorio

```bash
git clone <repository-url>
cd worksena-restaurant
```

### 2. Iniciar bases de datos con Docker

```bash
# Levantar todas las bases de datos
docker-compose up -d

# Verificar que estén corriendo
docker-compose ps
```

### 3. Configurar la aplicación

Elige tu base de datos preferida modificando `application.properties`:

```properties
# Para MySQL (puerto 3306)
spring.profiles.active=mysql

# Para PostgreSQL (puerto 5432)
spring.profiles.active=postgresql

# Para SQL Server (puerto 1433)
spring.profiles.active=sqlserver
```

### 4. Ejecutar la aplicación

```bash
# Con Maven
mvnw spring-boot:run

# O con Docker
docker build -t worksena-restaurant .
docker run -p 8080:8080 worksena-restaurant
```

### 5. Acceder a la aplicación

- **API Principal**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/api-docs
- **phpMyAdmin** (MySQL): http://localhost:8081
- **pgAdmin** (PostgreSQL): http://localhost:8082

## 🗄️ Bases de Datos Disponibles

### MySQL
- **Puerto**: 3306
- **Base de datos**: worksena_restaurant
- **Usuario**: worksena_user
- **Contraseña**: worksena_pass
- **phpMyAdmin**: http://localhost:8081

### PostgreSQL
- **Puerto**: 5432
- **Base de datos**: worksena_restaurant
- **Usuario**: worksena_user
- **Contraseña**: worksena_pass
- **pgAdmin**: http://localhost:8082 (admin@worksena.com / admin123)

### SQL Server
- **Puerto**: 1433
- **Usuario**: sa
- **Contraseña**: StrongPassword123!

## 📚 API Endpoints

### 🍽️ Gestión del Menú
- `GET /api/menu/items` - Listar items del menú
- `POST /api/menu/items` - Crear item del menú
- `GET /api/menu/categories` - Listar categorías

### 📋 Gestión de Pedidos
- `GET /api/orders` - Listar pedidos
- `POST /api/orders` - Crear pedido
- `PUT /api/orders/{id}/status` - Actualizar estado del pedido

### 🧾 Facturación
- `GET /api/invoices` - Listar facturas
- `POST /api/invoices/generate/{orderId}` - Generar factura desde pedido
- `GET /api/invoices/reports/revenue` - Reportes de ingresos

### 🪑 Gestión de Mesas
- `GET /api/tables` - Listar mesas
- `POST /api/tables` - Crear mesa

### 📦 Inventario
- `GET /api/inventory/items` - Listar items de inventario
- `POST /api/inventory/items` - Crear item de inventario

### 🏪 Proveedores
- `GET /api/suppliers` - Listar proveedores
- `POST /api/suppliers` - Crear proveedor

### 📊 Reportes
- `GET /api/reports/sales` - Reportes de ventas
- `GET /api/reports/popular-items` - Items más populares

## 🐳 Comandos Docker Útiles

```bash
# Levantar servicios
docker-compose up -d

# Detener servicios
docker-compose down

# Ver logs
docker-compose logs -f

# Reiniciar base de datos específica
docker-compose restart mysql

# Limpiar volúmenes
docker-compose down -v
```

## 🔧 Configuración de Desarrollo

### Variables de Entorno

```bash
# Base de datos
SPRING_PROFILES_ACTIVE=mysql
DB_HOST=localhost
DB_PORT=3306
DB_NAME=worksena_restaurant
DB_USER=worksena_user
DB_PASSWORD=worksena_pass

# JWT (si se implementa autenticación)
JWT_SECRET=your-secret-key
JWT_EXPIRATION=86400000

# Logging
LOGGING_LEVEL_COM_TRABAJO_WORKSENA=DEBUG
```

### Perfiles de Spring

- `mysql` - Configuración para MySQL
- `postgresql` - Configuración para PostgreSQL
- `sqlserver` - Configuración para SQL Server

## 🏗️ Arquitectura

```
src/main/java/com/Trabajo/WorkSena/
├── Domain/           # Entidades adicionales
├── Infrastructure/   # Configuraciones, excepciones
├── Menu/            # Gestión del menú
├── Orders/          # Gestión de pedidos
├── Tables/          # Gestión de mesas
├── Inventory/       # Gestión de inventario
├── Suppliers/       # Gestión de proveedores
├── Invoices/        # Sistema de facturación
├── Reports/         # Reportes y estadísticas
└── WorkSenaApplication.java
```

## 🧪 Testing

```bash
# Ejecutar tests
mvnw test

# Ejecutar tests con cobertura
mvnw test jacoco:report
```

## 📦 Despliegue

### Con Docker

```bash
# Construir imagen
docker build -t worksena-restaurant .

# Ejecutar contenedor
docker run -p 8080:8080 --network worksena_network worksena-restaurant
```

### Con Docker Compose (Completo)

```bash
# Ambiente completo de desarrollo
docker-compose up -d

# Ambiente de producción
docker-compose -f docker-compose.prod.yml up -d
```

## 🤝 Contribución

1. Fork el proyecto
2. Crea tu rama de feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📝 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

## 📞 Soporte

Para soporte técnico, por favor contacta a:
- **Email**: support@worksena.com
- **Documentación**: http://localhost:8080/swagger-ui.html

---

⭐ **WorkSena Restaurant Management System** - Sistema completo para la gestión eficiente de restaurantes.