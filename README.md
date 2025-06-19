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

## API Usage Examples (with curl)

curl -X POST http://localhost:8080/setup \
  -H "Content-Type: application/json" \
  -d '{"type": "laptop", "hardwareId": 4708}'

Create a Server Setup
curl -X POST http://localhost:8080/setup \
  -H "Content-Type: application/json" \
  -d '{"type": "server", "hardwareId": 4611}'

Add a SATA Disk to a Laptop Setup
curl -X POST http://localhost:8080/setup/101/add \
  -H "Content-Type: application/json" \
  -d '{"type": "disk", "hardwareId": 4495}'

Try to Add SAS Disk to a Laptop (Expected to Fail)
curl -X POST http://localhost:8080/setup/101/add \
  -H "Content-Type: application/json" \
  -d '{"type": "disk", "hardwareId": 3003}'

```
