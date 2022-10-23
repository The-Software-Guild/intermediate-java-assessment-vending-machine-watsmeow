package com.watsmeow.VendingMachine.dao;

import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;
import java.util.Map;

public interface VendingMachineDaoFile {
    public Item unmarshallItem(String line);
    public String marshallItem(Item item);
    public void writeToItemsFile(List<Item> list, String file) throws VendingMachinePersistenceException;
    public Map<String,Item> loadVendingMachine(String file) throws VendingMachinePersistenceException;
}
