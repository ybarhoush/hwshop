package org.example.hwshop.repository;

import org.example.hwshop.controller.StorageController;
import org.example.hwshop.domain.Disk;
import org.example.hwshop.domain.Laptop;
import org.example.hwshop.domain.Server;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@Primary
@Profile("test") // Only use this for Cucumber or other tests
public class StubHardwareRepository implements HardwareRepository {

    @Override
    public Flux<Laptop> getAllLaptops() {
        return Flux.just(new Laptop(4708, "TestBrand", "TestModel", "i7", "16G", 1));
    }

    @Override
    public Flux<Server> getAllServers() {
        return Flux.just(new Server(4611, "HP", "DL380", "Xeon", "32G", 1, 1, 1));
    }

    @Override
    public Flux<Disk> getAllDisks() {
        return Flux.just(
                new Disk(4345, "WD", "SATAOne", "500G", "SATA"),       // valid for laptop
                new Disk(2075, "Hitachi", "Ultrastar", "73G", "SAS")   // invalid for laptop
        );
    }

    @Override
    public Flux<StorageController> getAllStorageControllers() {
        return Flux.just(
                new StorageController(3691, "LSI", "MegaRAID", "PCI", "SAS", 2), // valid
                new StorageController(4533, "Adaptec", "ASR-2120S", "PCI-X", "SCSI", 2) // invalid
        );
    }

}
