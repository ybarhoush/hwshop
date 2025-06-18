package org.example.hwshop.integration;

import com.jayway.jsonpath.JsonPath;
import org.example.hwshop.HwshopApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(
        classes = HwshopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class SetupControllerIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void shouldCreateLaptopSetupSuccessfully() {
        webTestClient.post().uri("/setup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "laptop", "hardwareId", 4708))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.manufacturer").isEqualTo("Fujitsu-Siemens")
                .jsonPath("$.diskPorts").isEqualTo(1)
                .jsonPath("$.storageControllers.length()").isEqualTo(0);
    }

    @Test
    void shouldAddSataDiskToLaptop() {
        int setupId = createLaptopSetup();

        // SATA disk: 4345
        webTestClient.post().uri("/setup/" + setupId + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "disk", "hardwareId", 4345))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.disks.length()").isEqualTo(1)
                .jsonPath("$.disks[0].port").isEqualTo("SATA");
    }

    @Test
    void shouldRejectSasDiskOnLaptop() {
        int setupId = createLaptopSetup();

        // SAS disk: 2075
        webTestClient.post().uri("/setup/" + setupId + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "disk", "hardwareId", 2075))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message").isEqualTo("Laptops only support SATA disks.");
    }

    @Test
    void shouldRejectPciXController() {
        int setupId = createServerSetup();

        // PCI-X controller: 4533
        webTestClient.post().uri("/setup/" + setupId + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "storageController", "hardwareId", 4533))
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .jsonPath("$.message").isEqualTo("PCI-X controllers are not supported.");
    }

    // --- Utility Methods ---

    private int createLaptopSetup() {
        byte[] responseBytes = webTestClient.post().uri("/setup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "laptop", "hardwareId", 4708))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBodyContent();

        assertNotNull(responseBytes);
        return JsonPath.read(new String(responseBytes, StandardCharsets.UTF_8), "$.id");
    }

    private int createServerSetup() {
        byte[] responseBytes = webTestClient.post().uri("/setup")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("type", "server", "hardwareId", 4611))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult()
                .getResponseBodyContent();

        assertNotNull(responseBytes);
        return JsonPath.read(new String(responseBytes, StandardCharsets.UTF_8), "$.id");
    }
}