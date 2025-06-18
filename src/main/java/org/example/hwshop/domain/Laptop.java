package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Laptop extends Hardware {

    @JsonProperty("Processor")
    private String processor;

    @JsonProperty("Memory")
    private String memory;

    @JsonProperty("Disk ports")
    private int diskPorts;
}
