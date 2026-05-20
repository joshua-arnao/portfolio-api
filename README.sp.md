**Seleccionar idioma:**
[🇺🇸 English](README.md) | [🇪🇸 Español](README.sp.md)

---

# 🗂️ Portfolio API — Joshua Arnao

Backend RESTful para mi portfolio personal, construido con Java 21, Spring Boot y PostgreSQL. Diseñado para servir contenido dinámico — proyectos, skills y experiencias — al frontend en React, sin necesidad de redeploy para actualizar el contenido.

---

## ¿Por qué un backend dedicado?

Mi portfolio funcionaba originalmente con AWS Lambda. A medida que el contenido creció en estructura — proyectos con secciones anidadas, skills agrupadas por categoría, experiencias con distintos tipos — un backend relacional real se convirtió en la decisión correcta.

Esta API reemplaza Lambda con un servicio Spring Boot respaldado por PostgreSQL, desplegado en el free tier de Render. Las actualizaciones de contenido ocurren a nivel de base de datos, no en el código.

---

## Arquitectura

El proyecto sigue una **estructura de paquetes por feature** — cada dominio es completamente autocontenido con sus propias capas, manteniendo las responsabilidades claras y el código navegable a medida que crece.

```
com.autodidacta.portafolioapi
    ├── project/            → Dominio de proyectos
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    ├── skill/              → Dominio de skills
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    ├── experience/         → Dominio de experiencias
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    └── shared/             → Concerns transversales
        ├── config/         → CORS, conversores JSON, seed de datos
        └── exceptions/     → Manejo global de errores
```

---

## Decisiones técnicas clave

### Records de Java para DTOs

Los objetos de respuesta usan Records de Java 21 — inmutables por diseño, sin boilerplate:

```java
public record ProjectResponse(
        Long id,
        String title,
        String description,
        String imgCard,
        Boolean featured,
        List<String> tools,
        List<String> type,
        List<String> link,
        List<String> rol,
        ProjectSection problem,
        ProjectSection understanding,
        ProjectSection breacking,
        ProjectSection overall,
        ProjectSection conclusions
) {}
```

### Columnas JSON con JPA AttributeConverter

Las secciones del proyecto (`problem`, `understanding`, `breacking`, `overall`, `conclusions`) se almacenan como columnas de texto JSON en PostgreSQL. En lugar de una tabla separada por sección — que implicaría cinco joins por consulta — cada sección se serializa en una sola columna usando un `AttributeConverter` personalizado:

```java
@Converter
public class JsonConverter implements AttributeConverter<ProjectSection, String> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(ProjectSection attribute) {
        return objectMapper.writeValueAsString(attribute);
    }

    @Override
    public ProjectSection convertToEntityAttribute(String dbData) {
        return objectMapper.readValue(dbData, ProjectSection.class);
    }
}
```

Esto mantiene el schema plano y las queries simples, preservando flexibilidad total para evolucionar la estructura de secciones sin migraciones.

### Configuración de CORS

Los orígenes están externalizados en variables de entorno, así el mismo binario corre localmente y en producción sin cambios en el código:

```properties
app.cors.allowed-origins=${CORS_ORIGINS:http://localhost:5173,https://joshua-arnao.netlify.app}
```

### Seed de datos

El contenido inicial se siembra con `CommandLineRunner` en el primer arranque — el chequeo `if (repository.count() == 0)` garantiza idempotencia entre reinicios:

```java
@Override
public void run(String... args) {
    if (skillRepository.count() == 0) {
        skillRepository.saveAll(List.of(...));
    }
}
```

---

## Stack tecnológico

| Tecnología        | Versión | Propósito                          |
|-------------------|---------|------------------------------------|
| Java              | 21      | Lenguaje — Records                 |
| Spring Boot       | 4.0.x   | Framework de aplicación            |
| Spring Data JPA   | —       | Persistencia de datos              |
| PostgreSQL        | 15      | Base de datos relacional           |
| Lombok            | —       | Reducción de boilerplate           |
| Jackson           | —       | Serialización JSON                 |
| SpringDoc OpenAPI | 2.8.6   | Documentación Swagger UI           |
| Docker            | —       | Contenedorización local PostgreSQL |
| Render            | —       | Deploy en la nube (free tier)      |

---

## Endpoints de la API

```
GET  /api/projects         → Todos los proyectos ordenados por sortOrder
GET  /api/projects/{id}    → Detalle de proyecto por ID
GET  /api/skills           → Todos los skills agrupados por tipo
GET  /api/experiences      → Todas las experiencias ordenadas por fecha
```

Documentación interactiva completa disponible en `/swagger-ui` una vez corriendo.

---

## Correr localmente

### Prerrequisitos

- Java 21
- Docker

### Pasos

**1. Clonar el repositorio**

```bash
git clone https://github.com/joshua-arnao/portfolio-api.git
cd portfolio-api
```

**2. Levantar PostgreSQL**

```bash
docker-compose up -d
```

**3. Correr la aplicación**

```bash
./mvnw spring-boot:run
```

**4. Abrir Swagger UI**

```
http://localhost:8080/swagger-ui
```

---

## Deploy en Render

Configura las siguientes variables de entorno en tu servicio de Render:

```
DB_URL         → jdbc:postgresql://<host>/<dbname>
DB_USERNAME    → tu_usuario_db
DB_PASSWORD    → tu_password_db
CORS_ORIGINS   → https://joshua-arnao.netlify.app
PORT           → 8080
```

---

## Lo que aprendí construyendo esto

Construir esta API me llevó a tomar decisiones deliberadas en lugar de las predeterminadas.

El enfoque de columnas JSON para las secciones del proyecto fue el tradeoff clave — cinco tablas extra habrían hecho el schema más "correcto" en términos relacionales, pero una sola fila plana por proyecto con columnas JSON es más simple de consultar, más simple de sembrar y más simple de evolucionar. Para una API de portfolio con lecturas frecuentes y sin escrituras concurrentes, ese tradeoff es claramente correcto.

Usar `AttributeConverter` en lugar de una librería externa como Hypersistence Utils también me enseñó que los puntos de extensión JPA de Spring Boot son más poderosos de lo que esperaba — raramente necesitas una dependencia extra cuando el spec estándar ya cubre el caso de uso.

---

## Autor

**Joshua Arnao Canessa**
Java Backend Developer · AI Engineering · Lima, Perú
[linkedin.com/in/joshua-arnao](https://pe.linkedin.com/in/joshua-arnao-canessa-832090213)
