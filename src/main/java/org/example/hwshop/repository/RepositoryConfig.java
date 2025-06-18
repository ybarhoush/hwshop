package org.example.hwshop.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public HardwareRepository hardwareRepository() {
        return new CsvHardwareRepository(); // manually create instance
    }
}
