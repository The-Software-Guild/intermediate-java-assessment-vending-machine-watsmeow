package com.watsmeow.VendingMachine.service;

import com.watsmeow.VendingMachine.dao.VendingMachinePersistenceException;
import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;

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
