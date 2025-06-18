package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}