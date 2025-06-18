package org.example.hwshop.repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.hwshop.domain.Laptop;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvHardwareRepositoryTest {

    @Test
    void shouldParseLaptopFromInlineCsv() throws Exception {
        String csv = """
            Warehouse ID,Manufacturer,Model,Processor,Memory,Disk ports
            4708,Fujitsu-Siemens,Esprimo D9500,x86 Core2,2G,1
            """;

        InputStream stream = new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8));
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaWithHeader().withColumnReordering(true);

        MappingIterator<Laptop> iterator = csvMapper.readerFor(Laptop.class)
                .with(schema)
                .readValues(stream);

        List<Laptop> laptops = iterator.readAll();

        assertEquals(1, laptops.size());
        assertEquals("Fujitsu-Siemens", laptops.get(0).getManufacturer());
        assertEquals("Esprimo D9500", laptops.get(0).getModel());
    }
}
