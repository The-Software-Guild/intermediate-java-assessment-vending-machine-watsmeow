package com.watsmeow.VendingMachine.dao;

import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;

public interface VendingMachineDao {

    /**
     * Method to create a new item,
     */
    Item createItem(Item item) throws VendingMachinePersistenceException;

    Item removeItem(String name) throws VendingMachinePersistenceException;

    Item getItem(String name) throws VendingMachinePersistenceException;

    void updateItem(String name, Item item) throws VendingMachinePersistenceException;

    List<Item> getAllItems() throws VendingMachinePersistenceException;

    void addItem(Item item) throws VendingMachinePersistenceException;
}
