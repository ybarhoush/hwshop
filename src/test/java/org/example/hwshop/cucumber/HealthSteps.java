package org.example.hwshop.cucumber;

import io.cucumber.java.en.*;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

public class HealthSteps {

    private WebClient.ResponseSpec response;

    @Given("the application is running")
    public void applicationIsRunning() {
        // Spring context started — nothing needed
    }

    @When("I call the health endpoint")
    public void callHealthEndpoint() {
        WebClient client = WebClient.create("http://localhost:8080");
        response = client.get().uri("/actuator/health").retrieve();
    }

    @Then("I receive a 200 OK response")
    public void verifyResponse() {
        Integer statusCode = response.toBodilessEntity()
                .map(resp -> resp.getStatusCode().value())
                .block();
        assertEquals(200, statusCode);
    }
}
