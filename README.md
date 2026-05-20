**Select Language:**
[🇺🇸 English](README.md) | [🇪🇸 Español](README.sp.md)

---

# 🗂️ Portfolio API — Joshua Arnao

A RESTful backend for my personal portfolio, built with Java 21, Spring Boot, and PostgreSQL. Designed to serve dynamic content — projects, skills, and experiences — to the React frontend without requiring a redeploy to update content.

---

## Why a dedicated backend?

My portfolio was originally powered by AWS Lambda. As the content grew more structured — projects with nested sections, skills grouped by category, experiences with different types — a proper relational backend became the right call.

This API replaces Lambda with a Spring Boot service backed by PostgreSQL, deployed on Render's free tier. Content updates happen at the database level, not in the codebase.

---

## Architecture

The project follows a **feature-based package structure** — each domain is fully self-contained with its own layers, keeping concerns clean and the codebase navigable as it grows.

```
com.autodidacta.portafolioapi
    ├── project/            → Project domain
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    ├── skill/              → Skill domain
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    ├── experience/         → Experience domain
    │   ├── controller/
    │   ├── service/
    │   ├── repository/
    │   ├── entity/
    │   └── dto/
    └── shared/             → Cross-cutting concerns
        ├── config/         → CORS, JSON converters, data seeding
        └── exceptions/     → Global error handling
```

---

## Key Technical Decisions

### Java Records for DTOs

Response objects use Java 21 Records — immutable by design, with no boilerplate:

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

### JSON Columns via JPA AttributeConverter

Project sections (`problem`, `understanding`, `breacking`, `overall`, `conclusions`) are stored as JSON text columns in PostgreSQL. Instead of a separate table per section — which would mean five joins per project query — each section serializes to a single column using a custom `AttributeConverter`:

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

This keeps the schema flat and the queries simple, while preserving full flexibility to evolve the section structure without migrations.

### CORS Configuration

Origins are externalized to environment variables, so the same binary runs locally and in production without code changes:

```properties
app.cors.allowed-origins=${CORS_ORIGINS:http://localhost:5173,https://joshua-arnao.netlify.app}
```

### Data Seeding

Initial content is seeded via `CommandLineRunner` on first boot — the check `if (repository.count() == 0)` guarantees idempotency across restarts:

```java
@Override
public void run(String... args) {
    if (skillRepository.count() == 0) {
        skillRepository.saveAll(List.of(...));
    }
}
```

---

## Tech Stack

| Technology        | Version | Purpose                        |
|-------------------|---------|--------------------------------|
| Java              | 21      | Language — Records             |
| Spring Boot       | 4.0.x   | Application framework          |
| Spring Data JPA   | —       | Database persistence           |
| PostgreSQL        | 15      | Relational database            |
| Lombok            | —       | Boilerplate reduction          |
| Jackson           | —       | JSON serialization             |
| SpringDoc OpenAPI | 2.8.6   | Swagger UI documentation       |
| Docker            | —       | Local PostgreSQL containerization |
| Render            | —       | Cloud deployment (free tier)   |

---

## API Endpoints

```
GET  /api/projects         → All projects ordered by sortOrder
GET  /api/projects/{id}    → Project detail by ID
GET  /api/skills           → All skills grouped by type
GET  /api/experiences      → All experiences ordered by date
```

Full interactive documentation available at `/swagger-ui` once running.

---

## Running Locally

### Prerequisites

- Java 21
- Docker

### Steps

**1. Clone the repository**

```bash
git clone https://github.com/joshua-arnao/portfolio-api.git
cd portfolio-api
```

**2. Start PostgreSQL**

```bash
docker-compose up -d
```

**3. Run the application**

```bash
./mvnw spring-boot:run
```

**4. Open Swagger UI**

```
http://localhost:8080/swagger-ui
```

---

## Deployment on Render

Set the following environment variables in your Render service:

```
DB_URL         → jdbc:postgresql://<host>/<dbname>
DB_USERNAME    → your_db_user
DB_PASSWORD    → your_db_password
CORS_ORIGINS   → https://joshua-arnao.netlify.app
PORT           → 8080
```

---

## What I Learned Building This

Building this API pushed me to make deliberate decisions rather than default ones.

The JSON column approach for project sections was the key tradeoff — five extra tables would have made the schema more "correct" by relational standards, but a single flat row per project with JSON columns is simpler to query, simpler to seed, and simpler to evolve. For a read-heavy portfolio API with no concurrent writes, that tradeoff is clearly right.

Using `AttributeConverter` instead of a third-party library like Hypersistence Utils also taught me that Spring Boot's built-in JPA extension points are more powerful than I expected — you rarely need an extra dependency when the standard spec already covers the use case.

---

## Author

**Joshua Arnao Canessa**
Java Backend Developer · AI Engineering · Lima, Perú
[linkedin.com/in/joshua-arnao](https://pe.linkedin.com/in/joshua-arnao-canessa-832090213)
