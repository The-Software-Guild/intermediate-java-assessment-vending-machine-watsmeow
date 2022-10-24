package com.watsmeow.VendingMachine.dao;


/*
* This class will implement the Dao.
* It will declare public methods that:
* - establish what the persistence file is
* - establish the delimiter that will be used when putting objects into the file
* - read from and write to the persistence file/marshall and unmarshall data to and from the persistence file
* - declare a hashmap that stores items in local memory
* - add and remove objects from memory, edit objects in memory */


import com.watsmeow.VendingMachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class VendingMachineDaoImpl implements VendingMachineDao {

    // Hashmap to store snacks in when unmarshalled from .txt file
    private Map<String, Item> items = new HashMap();

    // .txt file that stores snack info so that it can persist
    private static final String ITEM_FILE = "items.txt";

    // Saving the dao file into a variable
    VendingMachineDaoFile fio;

    // Constructor for the implementation, takes in the dao file and .txt file
    public VendingMachineDaoImpl() throws VendingMachinePersistenceException {
        fio = new VendingMachineDaoFileImpl();
        items = fio.loadVendingMachine(ITEM_FILE);
    }

    // Method to add a snack into the vending machine
    public void addItem(Item item) throws VendingMachinePersistenceException {
        items = fio.loadVendingMachine(ITEM_FILE);
        items.put(item.getName().toLowerCase(), item);
        fio.writeToItemsFile(new ArrayList<>(items.values()), ITEM_FILE);
    }

    // Method to remove a snack from the vending machine
    public Item removeItem(String name) throws VendingMachinePersistenceException {
        name = name.toLowerCase();
        items = fio.loadVendingMachine(ITEM_FILE);
        Item removedItem = items.remove(name);
        fio.writeToItemsFile(new ArrayList<>(items.values()), ITEM_FILE);
        return removedItem;
    }

    // Method to get a snack from the vending machine
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        name = name.toLowerCase();
        items = fio.loadVendingMachine(ITEM_FILE);
        return items.get(name);
    }

    // Method to update a snack in the vending machine
    @Override
    public void updateItem(String name, Item item) throws VendingMachinePersistenceException {
        name = name.toLowerCase();
        items = fio.loadVendingMachine(ITEM_FILE);
        if (name == item.getName().toLowerCase()) {
            items.put(name, item);
        } else {
            items.remove(name);
            items.put(item.getName().toLowerCase(), item);
        }
        fio.writeToItemsFile(new ArrayList<>(items.values()), ITEM_FILE);
    }

    // Method to get all snacks from the vending machine in list form
    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        items = fio.loadVendingMachine(ITEM_FILE);
        return new ArrayList<>(items.values());
    }


}
