package org.example.hwshop.cucumber;

import io.cucumber.java.en.*;
import org.example.hwshop.domain.*;
import org.example.hwshop.exception.SetupValidationException;
import org.example.hwshop.service.SetupService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

public class SetupSteps {

    @Autowired
    private SetupService setupService;

    private Setup setup;
    private Throwable error;

    @Given("a {word} setup with warehouse ID {long}")
    public void a_setup_with_id(String type, long warehouseId) {
        try {
            SetupType setupType = SetupType.valueOf(type.toUpperCase());
            setup = setupService.createSetup(setupType, warehouseId).block();
        } catch (Throwable t) {
            error = t;
        }
    }

    @When("I add disk with ID {long}")
    public void i_add_disk_with_id(long diskId) {
        try {
            setup = setupService.addDisk(setup.getId(), diskId).block();
        } catch (Throwable t) {
            error = t;
        }
    }

    @When("I add controller with ID {long}")
    public void i_add_controller_with_id(long controllerId) {
        try {
            setup = setupService.addController(setup.getId(), controllerId).block();
        } catch (Throwable t) {
            error = t;
        }
    }

    @Then("the setup should have manufacturer {string}")
    public void the_setup_should_have_manufacturer(String expected) {
        assertNotNull(setup);
        assertEquals(expected, setup.getLaptop() != null ?
                setup.getLaptop().getManufacturer() : setup.getServer().getManufacturer());
    }

    @Then("the setup should contain a disk with port {string}")
    public void setup_should_contain_disk_with_port(String port) {
        assertNotNull(setup);
        assertTrue(setup.getDisks().stream()
                .anyMatch(d -> d.getPort().equalsIgnoreCase(port)));
    }

    @Then("I should receive an error saying {string}")
    public void i_should_receive_error(String message) {
        assertNotNull(error);
        assertTrue(error instanceof SetupValidationException);
        assertEquals(message, error.getMessage());
    }
}
