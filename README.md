# Inditex Price API
This is my technical test for the senior software engineer position at GFT and the Inditex project. I hope I've completed all the requirements and am available to answer any questions. Thank you very much for the opportunity.

A REST API developed in Java 21 using Spring Boot and Hexagonal Architecture. It provides product pricing data with support for versioned endpoints (V1), in-memory H2 database, Swagger/OpenAPI documentation, and Docker support.

---

## 🧱 Tech Stack

- Java 21
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Lombok
- H2 Database
- Swagger (Springdoc OpenAPI)
- JUnit 5 + MockMvc
- Docker

---

## ⚙️ Prerequisites

Before you begin, ensure you have the following installed:
- JDK 21
- Maven 3.x
- Docker (optional, for running with Docker)

---

## 🚀 How to Run

### 1. Run locally with Maven

First, build the project and install the dependencies:
```bash
  ./mvnw clean install
```

Then, run the application:
```bash
  ./mvnw spring-boot:run
```

### 2. Run with Docker

#### 2.1 Create image
```bash
  docker build -t tech-test-inditex-price-api:0.0.1 .
```

#### 2.2 Create and run container
```bash
  docker run --name tech-test-inditex -p 8080:8080 tech-test-inditex-price-api:0.0.1
```

---

## 📚 Swagger

#### Swagger UI will be available at:
http://localhost:8080/swagger-ui.html

#### API docs (OpenAPI):
http://localhost:8080/v3/api-docs

---

## 🔍 Example Endpoint

```http request
http://localhost:8080/api/v1/prices?brandId=1&productId=35455&applicationDate=2020-06-14T10:00:00
```

---

## 🧪 Run Tests

### 1. Run unit and integration tests
```bash
  ./mvnw test
```

### 2. Run E2E tests with Postman

First, import the Postman collection and environment files located in `src/main/resources`:
- `postman-collection.json`
- `postman-environment.json`

Then, you can run the E2E tests included in the collection.
