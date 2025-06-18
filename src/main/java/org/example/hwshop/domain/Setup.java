package org.example.hwshop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hwshop.controller.StorageController;
import org.example.hwshop.controller.StorageControllerWithDisks;
import org.example.hwshop.exception.SetupValidationException;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Setup {
    private long id;
    private SetupType type;

    private Laptop laptop;
    private Server server;

    private List<Disk> disks = new ArrayList<>();
    private List<StorageControllerWithDisks> controllers = new ArrayList<>();

    public void addDisk(Disk disk) {
        if (type == SetupType.LAPTOP) {
            if (!"SATA".equalsIgnoreCase(disk.getPort())) {
                throw new SetupValidationException("Laptops only support SATA disks.");
            }
            if (disks.size() >= laptop.getDiskPorts()) {
                throw new SetupValidationException("Laptop has no available disk ports.");
            }
            disks.add(disk);
        } else {
            throw new SetupValidationException("Disk must be added via controller for servers.");
        }
    }

    public void addController(StorageController controller) {
        if (type == SetupType.LAPTOP) {
            throw new SetupValidationException("Laptops cannot use storage controllers.");
        }
        if ("PCI-X".equalsIgnoreCase(controller.getBus())) {
            throw new SetupValidationException("PCI-X controllers are not supported.");
        }
        controllers.add(new StorageControllerWithDisks(controller, new ArrayList<>()));
    }

    public void addDiskToController(long controllerId, Disk disk) {
        var wrapper = controllers.stream()
                .filter(c -> c.getController().getWarehouseId() == controllerId)
                .findFirst()
                .orElseThrow(() -> new SetupValidationException("Controller not found"));

        StorageController ctrl = wrapper.getController();

        boolean portTypeMatch =
                "SCSI".equalsIgnoreCase(disk.getPort()) && "SCSI".equalsIgnoreCase(ctrl.getDiskPortType()) ||
                        ("SAS".equalsIgnoreCase(disk.getPort()) || "SATA".equalsIgnoreCase(disk.getPort())) && "SAS".equalsIgnoreCase(ctrl.getDiskPortType());

        if (!portTypeMatch) {
            throw new SetupValidationException("Incompatible disk and controller port types.");
        }

        if (wrapper.getDisks().size() >= ctrl.getDiskPorts()) {
            throw new SetupValidationException("Controller has no available ports.");
        }

        wrapper.getDisks().add(disk);
    }
}
