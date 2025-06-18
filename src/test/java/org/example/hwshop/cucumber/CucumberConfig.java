package org.example.hwshop.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest(properties = "spring.profiles.active=test")
public class CucumberConfig {
}
