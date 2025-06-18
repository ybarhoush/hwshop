package org.example.hwshop.controller;

import org.example.hwshop.domain.Laptop;
import org.example.hwshop.domain.Setup;
import org.example.hwshop.domain.SetupType;
import org.example.hwshop.dto.SetupCreateRequest;
import org.example.hwshop.service.SetupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Mono;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@WebFluxTest(controllers = SetupController.class)
@Import(SetupControllerIntegrationTest.MockConfig.class)
public class SetupControllerIntegrationTest {

    @Autowired
    private WebTestClient client;

    @Autowired
    private SetupService setupService;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public SetupService setupService() {
            return mock(SetupService.class);
        }
    }

    @Test
    void testCreateLaptopSetup() {
        SetupCreateRequest request = new SetupCreateRequest();
        request.setType("laptop");
        request.setHardwareId(1001);

        Setup mockSetup = new Setup(1, SetupType.LAPTOP,
                new Laptop(1001, "Lenovo", "ThinkPad", "i7", "16G", 2),
                null,
                new ArrayList<>(),
                new ArrayList<>());

        when(setupService.createSetup(SetupType.LAPTOP, 1001)).thenReturn(Mono.just(mockSetup));

        client.post()
                .uri("/setup")
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.manufacturer").isEqualTo("Lenovo")
                .jsonPath("$.diskPorts").isEqualTo(2);
    }
}