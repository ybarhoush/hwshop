package org.example.hwshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SetupResponse {
    private long id;
    private long warehouseId;
    private String manufacturer;
    private String model;
    private String processor;
    private String memory;

    private Integer diskPorts; // nullable for server
    private List<DiskDTO> disks;

    private Integer pciBuses;
    private Integer pcixBuses;
    private Integer pcieBuses;
    private List<ControllerDTO> storageControllers;
}