# HWshop Builder

A Java application for building custom laptop or server configurations from CSV inventory

## Features

- Load hardware from CSV: laptops, servers, disks, and controllers
- Create and manage setups with validation rules
- Reactive REST API using Spring WebFlux
- DTO-based API contract
- Swagger UI at `/swagger-ui.html`
- Unit and integration tests
- Cucumber tests

Requirements

    Java 21

    Maven

Future Work
Cucumber test coverage
JPA database support
CLI or UI
Docker + CI setup
    
## Run the App

```bash
mvn spring-boot:run

## Run Tests

mvn test

