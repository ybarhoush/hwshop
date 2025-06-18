package org.example.hwshop.domain;

import org.example.hwshop.controller.StorageController;
import org.example.hwshop.exception.SetupValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class SetupTest {

    private Laptop laptop;
    private Server server;
    private Disk sataDisk;
    private Disk scsiDisk;
    private Disk sasDisk;

    private StorageController sasController;
    private StorageController scsiController;
    private StorageController pciXController;

    @BeforeEach
    void setUp() {
        laptop = new Laptop(1001, "Lenovo", "ThinkPad", "i7", "16G", 1);
        server = new Server(2002, "HP", "DL380", "Xeon", "32G", 2, 2, 2);

        sataDisk = new Disk(3001, "WD", "Blue", "500G", "SATA");
        scsiDisk = new Disk(3002, "IBM", "Ultra", "18G", "SCSI");
        sasDisk = new Disk(3003, "Seagate", "Savvio", "600G", "SAS");

        sasController = new StorageController(4001, "LSI", "MegaRAID", "PCI", "SAS", 2);
        scsiController = new StorageController(4002, "Adaptec", "SCSI123", "PCI-e", "SCSI", 1);
        pciXController = new StorageController(4003, "HighPoint", "Unsupported", "PCI-X", "SATA", 2);
    }

    @Test
    void laptopShouldAcceptOnlySataDisk() {
        Setup setup = new Setup(1, SetupType.LAPTOP, laptop, null, new ArrayList<>(), new ArrayList<>());

        setup.addDisk(sataDisk);
        assertEquals(1, setup.getDisks().size());
    }

    @Test
    void laptopShouldRejectNonSataDisk() {
        Setup setup = new Setup(1, SetupType.LAPTOP, laptop, null, new ArrayList<>(), new ArrayList<>());

        assertThrows(SetupValidationException.class, () -> setup.addDisk(scsiDisk));
        assertThrows(SetupValidationException.class, () -> setup.addDisk(sasDisk));
    }

    @Test
    void laptopShouldRejectMoreDisksThanPorts() {
        laptop.setDiskPorts(1);
        Setup setup = new Setup(1, SetupType.LAPTOP, laptop, null, new ArrayList<>(), new ArrayList<>());

        setup.addDisk(sataDisk);
        assertThrows(SetupValidationException.class, () -> setup.addDisk(new Disk(3004, "Samsung", "EVO", "1TB", "SATA")));
    }

    @Test
    void laptopShouldRejectStorageController() {
        Setup setup = new Setup(1, SetupType.LAPTOP, laptop, null, new ArrayList<>(), new ArrayList<>());

        assertThrows(SetupValidationException.class, () -> setup.addController(sasController));
    }

    @Test
    void serverShouldAcceptValidController() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());

        setup.addController(sasController);
        assertEquals(1, setup.getControllers().size());
    }

    @Test
    void serverShouldRejectPciXController() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());

        assertThrows(SetupValidationException.class, () -> setup.addController(pciXController));
    }

    @Test
    void serverShouldRejectDiskWithoutController() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());

        assertThrows(SetupValidationException.class, () -> setup.addDisk(sataDisk));
    }

    @Test
    void serverShouldAddDiskToMatchingSasController() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());
        setup.addController(sasController);
        setup.addDiskToController(sasController.getWarehouseId(), sataDisk);
        setup.addDiskToController(sasController.getWarehouseId(), sasDisk);

        assertEquals(2, setup.getControllers().get(0).getDisks().size());
    }

    @Test
    void serverShouldRejectSataOnScsiController() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());
        setup.addController(scsiController);

        assertThrows(SetupValidationException.class, () -> setup.addDiskToController(scsiController.getWarehouseId(), sataDisk));
    }

    @Test
    void controllerShouldRejectTooManyDisks() {
        StorageController singlePortCtrl = new StorageController(9000, "Mini", "TinyCtrl", "PCI", "SAS", 1);
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());

        setup.addController(singlePortCtrl);
        setup.addDiskToController(9000, sasDisk);

        assertThrows(SetupValidationException.class, () -> setup.addDiskToController(9000, sataDisk));
    }

    @Test
    void shouldRejectUnknownControllerId() {
        Setup setup = new Setup(1, SetupType.SERVER, null, server, new ArrayList<>(), new ArrayList<>());
        setup.addController(sasController);

        assertThrows(SetupValidationException.class, () -> setup.addDiskToController(9999L, sataDisk));
    }
}
