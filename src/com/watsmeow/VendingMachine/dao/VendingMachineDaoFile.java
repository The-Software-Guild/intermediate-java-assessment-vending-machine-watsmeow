package com.watsmeow.VendingMachine.dao;

import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;
import java.util.Map;

public interface VendingMachineDaoFile {

    /**
     * Retrieves all snacks from the items.txt file
     * @param line is the item being retrieved as stored as a string in the .txt file
     * @return the selected item object
     */
    public Item unmarshallItem(String line);

    /**
     * Marshals the item hashmap into strings for storage in the .txt file and later retrieval
     * @param item is the snack object being stored in the file
     * @return the selected item object as a string with fields delimited by ::
     */
    public String marshallItem(Item item);

    /**
     * Writes all snacks in the vending machine to the .txt file based on the loadVendingMachine file format
     * Throws an exception if it cannot write the items to the file
     * @param list is the list of snacks it is iterating over and marshalling into the .txt file
     * @param file is the file in string format that's being written to
     */
    public void writeToItemsFile(List<Item> list, String file) throws VendingMachinePersistenceException;

    /**
     * Loads all the items from the .txt file into the hashmap
     * @param file is the file in string format that's being written to
     * @return is a hashmap of snacks, the key is the snack name, the value is the snack object
     */
    public Map<String,Item> loadVendingMachine(String file) throws VendingMachinePersistenceException;
}
