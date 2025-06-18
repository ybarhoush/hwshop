Feature: Laptop and server setup validation

  Scenario: Creating a laptop setup
    Given a laptop setup with warehouse ID 4708
    Then the setup should have manufacturer "TestBrand"

  Scenario: Adding a SATA disk to laptop
    Given a laptop setup with warehouse ID 4708
    When I add disk with ID 4345
    Then the setup should contain a disk with port "SATA"

  Scenario: Adding a SAS disk to laptop (invalid)
    Given a laptop setup with warehouse ID 4708
    When I add disk with ID 2075
    Then I should receive an error saying "Laptops only support SATA disks."

  Scenario: Adding a PCI-X controller to server (invalid)
    Given a server setup with warehouse ID 4611
    When I add controller with ID 4533
    Then I should receive an error saying "PCI-X controllers are not supported."
