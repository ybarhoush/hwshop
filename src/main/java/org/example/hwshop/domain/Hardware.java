package org.example.hwshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Hardware {

    @JsonProperty("Warehouse ID")
    protected long warehouseId;

    @JsonProperty("Manufacturer")
    protected String manufacturer;

    @JsonProperty("Model")
    protected String model;
}

