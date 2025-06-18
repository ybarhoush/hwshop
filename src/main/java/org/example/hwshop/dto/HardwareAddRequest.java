package org.example.hwshop.dto;

import lombok.Data;

@Data
public class HardwareAddRequest {
    private String type; // "disk" or "storageController"
    private long hardwareId;
}
