package org.example.hwshop.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hwshop.domain.Disk;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StorageControllerWithDisks {
    private StorageController controller;
    private List<Disk> disks = new ArrayList<>();
}
