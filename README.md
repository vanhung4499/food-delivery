# Food Delivery

> Delivery Order Service API

## 📣 Project Purpose

A small backend project to understand how a delivery order service work.

## 🛠 Tech Stack

### Development Environment

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- Gradle
- JUnit5
- QueryDSL
- MySQL 8.0

### Tools

- IntelliJ IDEA
- Git
- GitHub
- Docker
- Swagger 3.0 (OpenAPI 3.0)
- Obsidian

## 📦 How to run

1. Clone this repository
2. Run `docker-compose up -d` to run MySQL container
3. Run `./gradlew buid` to build the project
4. Run `./gradlew bootRun` to run the project

If you use IntelliJ IDEA, you can run the project by clicking the `Run` button. Remember build the project before run because of QueryDSL.

## 🚀 Features

- [x] User (Authentication and Authorization)
- [x] Shop Management
- [x] Menu Management
- [x] Order Management
- [x] Delivery Management
- [x] Rider Management
- [x] API Documentation
- [ ] Unit Test
- [ ] Deploy to AWS
- [ ] CI/CD (GitHub Actions)


## 📝 Project Structure

This project is follow DDD(Domain Driven Design) architecture.
All domain are: `delivery`, `order`, `rider`, `shop`, `menu`, `user`.

The project is divided into:

- `global`: Global configuration, common classes, error classes, and utilities

- Each module has:
  - `controller`: API endpoints
  - `dao`: Data access objects, repository implementations if needed
  - `domain`: Domain entities, repository interfaces, enum types, business requirements
  - `service`: Business logic
  - `dto`: `request` and `response` for API request and response

## 📚 Database ERD

Coming soon...

## 📚 API Documentation

After run the application, you can see the API documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 👓 Deploy

Coming soon...

## 📝 References

- [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security Reference](https://docs.spring.io/spring-security/site/docs/current/reference/html5/)
- [Spring Data JPA Reference](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
- [QueryDSL Reference](https://querydsl.com/static/querydsl/4.4.0/reference/html_single/)
- [Gradle User Guide](https://docs.gradle.org/current/userguide/userguide.html)
- [JUnit5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [MySQL Reference](https://dev.mysql.com/doc/refman/8.0/en/)

## 📝 License

This project is licensed under the terms of the MIT license.