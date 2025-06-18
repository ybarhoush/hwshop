package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageController extends Hardware {

    @JsonProperty("Bus")
    private String bus;

    @JsonProperty("Disk port type")
    private String diskPortType;

    @JsonProperty("Disk ports")
    private int diskPorts;

    public StorageController(long warehouseId, String manufacturer, String model,
                             String bus, String diskPortType, int diskPorts) {
        super(warehouseId, manufacturer, model);
        this.bus = bus;
        this.diskPortType = diskPortType;
        this.diskPorts = diskPorts;
    }
}