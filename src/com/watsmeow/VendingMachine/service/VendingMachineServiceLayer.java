package com.watsmeow.VendingMachine.service;

import com.watsmeow.VendingMachine.dao.VendingMachinePersistenceException;
import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;

/**
 * This class takes in methods from the dao, validates them, and then writes to the audit log
 * so that updates are properly tracked
 */
public interface VendingMachineServiceLayer {

    void addItem(Item item) throws VendingMachinePersistenceException, VendingMachineDuplicateIdException;
    Item getItem(String name) throws
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException,
            VendingMachinePersistenceException;

    void updateItem(String name, Item item) throws
            VendingMachinePersistenceException, VendingMachineDataValidationException;

    List<Item> getAllItems() throws
            VendingMachinePersistenceException;
}
