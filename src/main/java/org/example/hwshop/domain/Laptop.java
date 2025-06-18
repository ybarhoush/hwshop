package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Laptop extends Hardware {

    @JsonProperty("Processor")
    private String processor;

    @JsonProperty("Memory")
    private String memory;

    @JsonProperty("Disk ports")
    private int diskPorts;

    public Laptop(long warehouseId, String manufacturer, String model,
                  String processor, String memory, int diskPorts) {
        super(warehouseId, manufacturer, model);
        this.processor = processor;
        this.memory = memory;
        this.diskPorts = diskPorts;
    }
}
