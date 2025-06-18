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

