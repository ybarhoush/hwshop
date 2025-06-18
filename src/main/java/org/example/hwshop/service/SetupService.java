package org.example.hwshop.service;

import lombok.RequiredArgsConstructor;
import org.example.hwshop.domain.Setup;
import org.example.hwshop.domain.SetupType;
import org.example.hwshop.exception.SetupValidationException;
import org.example.hwshop.repository.HardwareRepository;
import org.example.hwshop.repository.SetupRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class SetupService {

    private final SetupRepository repository;
    private final HardwareRepository hardwareRepository;

    public Mono<Setup> createSetup(SetupType type, long hardwareId) {
        return switch (type) {
            case LAPTOP -> hardwareRepository.getAllLaptops()
                    .filter(l -> l.getWarehouseId() == hardwareId)
                    .next()
                    .map(l -> new Setup(0, SetupType.LAPTOP, l, null, new ArrayList<>(), new ArrayList<>()))
                    .map(repository::save);

            case SERVER -> hardwareRepository.getAllServers()
                    .filter(s -> s.getWarehouseId() == hardwareId)
                    .next()
                    .map(s -> new Setup(0, SetupType.SERVER, null, s, new ArrayList<>(), new ArrayList<>()))
                    .map(repository::save);
        };
    }

    public Mono<Setup> addDisk(long setupId, long diskId) {
        Setup setup = repository.findById(setupId)
                .orElseThrow(() -> new SetupValidationException("Setup not found"));

        return hardwareRepository.getAllDisks()
                .filter(d -> d.getWarehouseId() == diskId)
                .next()
                .doOnNext(setup::addDisk)
                .thenReturn(setup);
    }

    public Mono<Setup> addController(long setupId, long controllerId) {
        Setup setup = repository.findById(setupId)
                .orElseThrow(() -> new SetupValidationException("Setup not found"));

        return hardwareRepository.getAllStorageControllers()
                .filter(c -> c.getWarehouseId() == controllerId)
                .next()
                .doOnNext(setup::addController)
                .thenReturn(setup);
    }

    public Mono<Setup> addDiskToController(long setupId, long controllerId, long diskId) {
        Setup setup = repository.findById(setupId)
                .orElseThrow(() -> new SetupValidationException("Setup not found"));

        return hardwareRepository.getAllDisks()
                .filter(d -> d.getWarehouseId() == diskId)
                .next()
                .doOnNext(disk -> setup.addDiskToController(controllerId, disk))
                .thenReturn(setup);
    }
}
