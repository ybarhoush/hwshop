package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Server extends Hardware {

    @JsonProperty("Processor")
    private String processor;

    @JsonProperty("Memory")
    private String memory;

    @JsonProperty("PCI buses")
    private int pciBuses;

    @JsonProperty("PCI-X buses")
    private int pcixBuses;

    @JsonProperty("PCI-e buses")
    private int pcieBuses;

    public Server(long warehouseId, String manufacturer, String model,
                  String processor, String memory, int pciBuses, int pcixBuses, int pcieBuses) {
        super(warehouseId, manufacturer, model);
        this.processor = processor;
        this.memory = memory;
        this.pciBuses = pciBuses;
        this.pcixBuses = pcixBuses;
        this.pcieBuses = pcieBuses;
    }
}