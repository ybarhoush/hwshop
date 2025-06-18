## Sprint 1
### Setup
| Task     | Description                                                      |
| -------- | ---------------------------------------------------------------- |
| `pom.xml` | Setup Maven with Spring Boot, WebFlux, Lombok, Test deps         |
| Base Structure | Packages: `domain`, `service`, `controller`, `dto`, `repository` |
| Changelog | Create `CHANGELOG.md`                                            |
| UML      | Create `docs/uml.puml`                                           |
| Git      | Commit in `feature/init-project` branch                          |
| Sample Test | Hello-world Cucumber test stub                                   |

## Sprint 2
### CSV Loader + Domain Foundation
- Define final annotated domain models extending Hardware
- Implement CSV loader with CsvHardwareRepository
- Use inline unit tests for CSV parsing (no file dependency)

## Sprint 3
### Setup domain logic
- Models the Setup entity (a build of type server or laptop)
- Enforces valid hardware configuration rules
- Supports attaching disks and storage controllers
- Prep for API

## Sprint 4
### API
- Create setups (POST /setup)
- Add disks or controllers (POST /setup/{id}/add)
- Adds disks to a specific controller (POST /setup/{id}/controller/{controllerId}/add)
- Returns full setup state in the response