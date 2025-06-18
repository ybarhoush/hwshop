package org.example.hwshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ServerCsvDTO {
    @JsonProperty("Warehouse ID") private long warehouseId;
    @JsonProperty("Manufacturer") private String manufacturer;
    @JsonProperty("Model") private String model;
    @JsonProperty("Processor") private String processor;
    @JsonProperty("Memory") private String memory;
    @JsonProperty("PCI buses") private int pciBuses;
    @JsonProperty("PCI-X buses") private int pcixBuses;
    @JsonProperty("PCI-e buses") private int pcieBuses;
}
