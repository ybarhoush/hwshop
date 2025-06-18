package org.example.hwshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ControllerDTO {
    private long id;
    private String manufacturer;
    private String model;
    private String bus;
    private String diskPortType;
    private int diskPorts;
    private List<DiskDTO> disks;
}
