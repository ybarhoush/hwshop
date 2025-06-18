package org.example.hwshop.cucumber;

import org.example.hwshop.HwshopApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        classes = HwshopApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class CucumberTestContext {
    // Shared context if needed later
}
