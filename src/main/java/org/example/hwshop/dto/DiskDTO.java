package org.example.hwshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiskDTO {
    private long id;
    private String manufacturer;
    private String model;
    private String size;
    private String port;
}
