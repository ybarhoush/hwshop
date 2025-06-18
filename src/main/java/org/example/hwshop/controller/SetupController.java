package org.example.hwshop.controller;

import org.example.hwshop.domain.Setup;
import org.example.hwshop.domain.SetupType;
import org.example.hwshop.domain.Disk;
import org.example.hwshop.dto.*;
import org.example.hwshop.service.SetupService;
import org.example.hwshop.exception.SetupValidationException;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping("/setup")
@RequiredArgsConstructor
public class SetupController {

    private final SetupService setupService;

    @PostMapping
    public Mono<SetupResponse> createSetup(@RequestBody SetupCreateRequest request) {
        SetupType type = SetupType.valueOf(request.getType().toUpperCase());

        return setupService.createSetup(type, request.getHardwareId())
                .map(this::toResponse);
    }

    @PostMapping("/{id}/add")
    public Mono<SetupResponse> addHardware(
            @PathVariable long id,
            @RequestBody HardwareAddRequest request) {

        if ("disk".equalsIgnoreCase(request.getType())) {
            return setupService.addDisk(id, request.getHardwareId())
                    .map(this::toResponse);
        } else if ("storageController".equalsIgnoreCase(request.getType())) {
            return setupService.addController(id, request.getHardwareId())
                    .map(this::toResponse);
        } else {
            return Mono.error(new SetupValidationException("Invalid type"));
        }
    }

    @PostMapping("/{id}/controller/{controllerId}/add")
    public Mono<SetupResponse> addDiskToController(
            @PathVariable long id,
            @PathVariable long controllerId,
            @RequestBody HardwareAddRequest request) {

        return setupService.addDiskToController(id, controllerId, request.getHardwareId())
                .map(this::toResponse);
    }

    private SetupResponse toResponse(Setup setup) {
        if (setup.getType() == SetupType.LAPTOP) {
            return new SetupResponse(
                    setup.getId(),
                    setup.getLaptop().getWarehouseId(),
                    setup.getLaptop().getManufacturer(),
                    setup.getLaptop().getModel(),
                    setup.getLaptop().getProcessor(),
                    setup.getLaptop().getMemory(),
                    setup.getLaptop().getDiskPorts(),
                    setup.getDisks().stream().map(this::toDisk).toList(),
                    null, null, null,
                    List.of()
            );
        }

        return new SetupResponse(
                setup.getId(),
                setup.getServer().getWarehouseId(),
                setup.getServer().getManufacturer(),
                setup.getServer().getModel(),
                setup.getServer().getProcessor(),
                setup.getServer().getMemory(),
                null,
                List.of(),
                setup.getServer().getPciBuses(),
                setup.getServer().getPcixBuses(),
                setup.getServer().getPcieBuses(),
                setup.getControllers().stream()
                        .map(this::toController)
                        .toList()
        );
    }

    private DiskDTO toDisk(Disk disk) {
        return new DiskDTO(
                disk.getWarehouseId(),
                disk.getManufacturer(),
                disk.getModel(),
                disk.getSize(),
                disk.getPort()
        );
    }

    private ControllerDTO toController(StorageControllerWithDisks ctrl) {
        return new ControllerDTO(
                ctrl.getController().getWarehouseId(),
                ctrl.getController().getManufacturer(),
                ctrl.getController().getModel(),
                ctrl.getController().getBus(),
                ctrl.getController().getDiskPortType(),
                ctrl.getController().getDiskPorts(),
                ctrl.getDisks().stream().map(this::toDisk).toList()
        );
    }
}
