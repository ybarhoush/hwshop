package org.example.hwshop.dto;

import lombok.Data;

@Data
public class SetupCreateRequest {
    private String type; // "laptop" or "server"
    private long hardwareId;
}
