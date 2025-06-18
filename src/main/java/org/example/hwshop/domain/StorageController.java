package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StorageController extends Hardware {

    @JsonProperty("Bus")
    private String bus;

    @JsonProperty("Disk port type")
    private String diskPortType;

    @JsonProperty("Disk ports")
    private int diskPorts;
}