package org.example.hwshop.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.hwshop.domain.*;

import org.springframework.core.io.ClassPathResource;
import reactor.core.publisher.Flux;

import java.io.InputStream;
import java.util.List;

public class CsvHardwareRepository implements HardwareRepository {

    private final CsvMapper csvMapper = new CsvMapper();

    @Override
    public Flux<Server> getAllServers() {
        return Flux.fromIterable(read("servers.csv", Server.class));
    }

    @Override
    public Flux<Laptop> getAllLaptops() {
        return Flux.fromIterable(read("laptops.csv", Laptop.class));
    }

    @Override
    public Flux<Disk> getAllDisks() {
        return Flux.fromIterable(read("hdds.csv", Disk.class));
    }

    @Override
    public Flux<StorageController> getAllStorageControllers() {
        return Flux.fromIterable(read("hbas.csv", StorageController.class));
    }

    private <T> List<T> read(String path, Class<T> type) {
        try (InputStream stream = new ClassPathResource(path).getInputStream()) {
            CsvSchema schema = csvMapper.schemaWithHeader().withColumnReordering(true);
            return csvMapper.readerFor(type).with(schema).<T>readValues(stream).readAll();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read CSV: " + path, e);
        }
    }
}