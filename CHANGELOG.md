# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

---
## [0.5.1] - Test & BDD Build Support
- Updated `pom.xml` prepping for cucumber

## [0.5.0] - Integration Testing Complete
- Added WebTestClient integration tests for SetupController
- Tests include hardware compatibility validation (SATA, PCI-X)
- SetupValidationException returned as structured JSON

## [0.4.0] - REST API & DTO Contract Layer
- REST endpoints for setup lifecycle:
    - `POST /setup` — create laptop or server setup
    - `POST /setup/{id}/add` — add disk or storage controller
    - `POST /setup/{id}/controller/{controllerId}/add` — attach disk to controller
- `SetupController` with full reactive flow
- `SetupCreateRequest`, `HardwareAddRequest` request DTOs
- `SetupResponse`, `DiskDTO`, `ControllerDTO` for clean response contracts

## [0.3.0] - Setup Logic Complete
- Implemented `Setup` aggregate with full validation logic
- Created `SetupRepository` to store setup states
- Built `SetupService` with create and add methods
- Added `SetupValidationException` for rule enforcement
- Supported adding disks and controllers with checks
- Added complete unit tests for all Setup validation logic
- Covered disk compatibility, port limits, and controller rules

## [0.2.0] - CSV Parsing Layer Complete
- Defined abstract `Hardware` class with shared fields
- Extended `Laptop`, `Server`, `Disk`, `StorageController`
- Implemented `CsvHardwareRepository`
- Added inline CSV parsing tests using Jackson

## [0.1.0] - Initial Bootstrap
- Initialized Maven project using Spring Boot 3 + WebFlux
- Created reactive project structure (DDD style)
- Added base Cucumber test
- Added changelog and PlantUML diagram stub