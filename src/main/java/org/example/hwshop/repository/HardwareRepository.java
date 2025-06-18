package org.example.hwshop.repository;

import org.example.hwshop.domain.*;
import reactor.core.publisher.Flux;

public interface HardwareRepository {
    Flux<Server> getAllServers();
    Flux<Laptop> getAllLaptops();
    Flux<Disk> getAllDisks();
    Flux<StorageController> getAllStorageControllers();
}