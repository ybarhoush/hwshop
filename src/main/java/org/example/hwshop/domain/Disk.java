package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Disk extends Hardware {

    @JsonProperty("Size")
    private String size;

    @JsonProperty("Port")
    private String port;

    public Disk(long warehouseId, String manufacturer, String model,
                String size, String port) {
        super(warehouseId, manufacturer, model);
        this.size = size;
        this.port = port;
    }
}