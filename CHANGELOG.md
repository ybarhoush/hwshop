# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/).

---

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