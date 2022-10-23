package test.java.com.watsmeow.VendingMachine.dao;

import java.math.BigDecimal;
import java.util.List;
import com.watsmeow.VendingMachine.dao.VendingMachineDaoImpl;
import com.watsmeow.VendingMachine.dto.Item;
import org.junit.jupiter.api.Assertions;


public class VendingMachineDaoImplTest {
    public static VendingMachineDaoImpl testDao;

    public VendingMachineDaoImplTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
        testDao = new VendingMachineDaoImpl();
        Item item = new Item("Funions", new BigDecimal(2.50), 10);
        testDao.addItem(item);
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
        testDao = new VendingMachineDaoImpl();
        Item item = new Item("Funions", new BigDecimal(2.50), 10);
        testDao.addItem(item);
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of getItem method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetItem() throws Exception {
        Item garbageItem = testDao.getItem("garbageGarbage   garbage");
        Item garbageItem2 = testDao.getItem("Funions");
        Assertions.assertNull(garbageItem);
        Assertions.assertNotNull(garbageItem2);
    }

    /**
     * Test of listAllItems method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testGetAllItems() throws Exception {
        List<Item> testList = testDao.getAllItems();
        Assertions.assertTrue(testList.size() > 0);
        //implement
    }

    /**
     * Test of changeInventoryCount method, of class VendingMachineDaoImpl.
     */
    @org.junit.jupiter.api.Test
    public void testUpdateItem() throws Exception {
        Item item = new Item("Funions", new BigDecimal(5.50), 12);
        testDao.updateItem(item.getName(), item);
        Item updatedItem = testDao.getItem("Funions");
        Assertions.assertEquals(item.getName(), updatedItem.getName());
        Assertions.assertEquals(item.getCost(), updatedItem.getCost());
        Assertions.assertEquals(item.getQuantity(), updatedItem.getQuantity());
    }

}
