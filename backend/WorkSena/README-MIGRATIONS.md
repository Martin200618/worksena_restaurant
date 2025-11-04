# WorkSena Database Migrations

Este proyecto está configurado para trabajar con múltiples bases de datos usando perfiles de Spring Boot.

## Estructura de Migraciones

```
src/main/resources/db/migration/
├── mysql/
│   └── V1__init_mysql.sql
└── postgresql/
    └── V1__init_postgresql.sql
```

## Cómo Ejecutar Migraciones

### Opción 1: Script Automático (Recomendado)

**Windows:**
```cmd
migrate-databases.bat
```

**Linux/Mac:**
```bash
./migrate-databases.sh
```

### Opción 2: Migración Manual por Base de Datos

**MySQL:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```

**PostgreSQL:**
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=postgresql
```

### Opción 3: Usar IDE

En tu IDE, configura los perfiles en las opciones de ejecución:
- **Profile:** `mysql` o `postgresql`

## Configuración de Bases de Datos

### MySQL (XAMPP)
- **Host:** localhost:3306
- **Database:** worksena_restaurant
- **User:** root
- **Password:** (vacía)

### PostgreSQL
- **Host:** localhost:5432
- **Database:** worksena_restaurant
- **User:** postgres
- **Password:** postgres

## Notas Importantes

1. **Crear las bases de datos manualmente** antes de ejecutar las migraciones
2. Las migraciones usan `ddl-auto=create` para desarrollo
3. En producción, cambiar a `ddl-auto=validate` o `update`
4. Cada perfil tiene su propia ubicación de migraciones Flyway

## Agregar Nuevas Migraciones

1. Crear archivo SQL en la carpeta correspondiente:
   - MySQL: `src/main/resources/db/migration/mysql/V2__nombre_migracion.sql`
   - PostgreSQL: `src/main/resources/db/migration/postgresql/V2__nombre_migracion.sql`

2. Seguir la convención de nomenclatura: `V{numero}__{descripcion}.sql`

3. Ejecutar las migraciones nuevamente