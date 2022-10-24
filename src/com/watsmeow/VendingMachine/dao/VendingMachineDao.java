package com.watsmeow.VendingMachine.dao;

import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    /**
     * Method to create a new item, is checked by service layer to make sure
     * duplicates are not created
     * @param item is the snack being added
     * @return the snack object
     */
    void addItem(Item item) throws VendingMachinePersistenceException;

    /**
     * Removes a snack from the vending machine using the snack name
     * @param name is the name of the snack being deleted
     * @return returns removed item
     */
    Item removeItem(String name) throws VendingMachinePersistenceException;

    /**
     * Retrieves an item from the vending machine using the items name
     * @param name is the name of the snack being retrieved
     * @return the selected item
     */
    Item getItem(String name) throws VendingMachinePersistenceException;

    /**
     * Updates an item from the vending machine using the items name
     * @param name is the name of the snack being updated used to find the snack object
     * @param item is the snack object that is being updated
     * @return the selected item
     */
    void updateItem(String name, Item item) throws VendingMachinePersistenceException;

    /**
     * Returns a list of all snacks in the vending machine
     * @return List containing all snacks in vending machine
     */
    List<Item> getAllItems() throws VendingMachinePersistenceException;

}
