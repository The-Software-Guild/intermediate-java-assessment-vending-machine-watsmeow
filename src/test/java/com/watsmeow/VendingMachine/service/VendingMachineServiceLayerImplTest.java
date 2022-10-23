package test.java.com.watsmeow.VendingMachine.service;

import com.watsmeow.VendingMachine.dao.*;
import com.watsmeow.VendingMachine.service.VendingMachineDataValidationException;
import com.watsmeow.VendingMachine.service.VendingMachineDuplicateIdException;
import com.watsmeow.VendingMachine.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;

import com.watsmeow.VendingMachine.dto.Item;
import com.watsmeow.VendingMachine.service.VendingMachineServiceLayerImpl;
import org.junit.jupiter.api.Assertions;

public class VendingMachineServiceLayerImplTest {

    public static VendingMachineServiceLayer service;

    public VendingMachineServiceLayerImplTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws VendingMachinePersistenceException, VendingMachineDuplicateIdException {
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        dao.removeItem("Funions");
        Item item = new Item("Funions", new BigDecimal(2.50), 10);
        service.addItem(item);
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws VendingMachinePersistenceException {
        VendingMachineDao dao = new VendingMachineDaoImpl();
        dao.removeItem("Funions");
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws VendingMachinePersistenceException, VendingMachineDuplicateIdException {
        VendingMachineDao dao = new VendingMachineDaoImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
        dao.removeItem("Funions");
        Item item = new Item("Funions", new BigDecimal(2.50), 10);
        service.addItem(item);
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() {
    }

    /**
     * Test of getItem method, of class VendingMachineServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetItem() throws Exception {
        Item garbageItem = service.getItem("garbageGarbage   garbage");
        Item garbageItem2 = service.getItem("Funions");
        Assertions.assertNull(garbageItem);
        Assertions.assertNotNull(garbageItem2);
    }

    /**
     * Test of listAllItems method, of class VendingMachineServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllItems() throws Exception {
        List<Item> testList = service.getAllItems();
        Assertions.assertTrue(testList.size() > 0);
        //implement
    }

    /**
     * Test of changeInventoryCount method, of class VendingMachineServiceLayerImpl.
     */
    @org.junit.jupiter.api.Test
    public void testUpdateItem() throws Exception {
        Item item = new Item("Funions", new BigDecimal(5.50), 12);
        service.updateItem(item.getName(), item);
        Item updatedItem = service.getItem("Funions");
        Assertions.assertEquals(item.getName(), updatedItem.getName());
        Assertions.assertEquals(item.getCost(), updatedItem.getCost());
        Assertions.assertEquals(item.getQuantity(), updatedItem.getQuantity());
        item.setQuantity(-100);
        Assertions.assertThrows(
                VendingMachineDataValidationException.class,
                () -> service.updateItem(item.getName(), item));
    }
}
