Feature: Hello World

  Scenario: Hello
    Given the application is up
    When I call the health endpoint
    Then I get a 200 response