package com.watsmeow.VendingMachine.service;

import com.watsmeow.VendingMachine.dao.VendingMachineAuditDao;
import com.watsmeow.VendingMachine.dao.VendingMachineDao;
import com.watsmeow.VendingMachine.dao.VendingMachinePersistenceException;
import com.watsmeow.VendingMachine.dto.Item;

import java.util.List;
import java.util.stream.Collectors;

public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    // Adding a member file of the audit dao
    private VendingMachineAuditDao auditDao;

    // Service layer implementation constructor initializes the audit dao
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
        this.dao = dao;
    }

    /* Checks to see if the snack already exists and if so, throws an exception
    * so that duplicate snacks can't be added to the vending machine
    */
    public void addItem(Item item) throws VendingMachinePersistenceException,
            VendingMachineDuplicateIdException {
        if (dao.getItem(item.getName()) != null) {
            throw new VendingMachineDuplicateIdException(
                    "ERROR: Could not create item. Item "
                    + item.getName()
                    + " already exists."
            );
        }
        dao.addItem(item);
        auditDao.writeAuditEntry("Snack " + item.getName() + " ADDED.");
    }

    // Writing an entry to the audit log when a snack is removed from the Vending Machine
    public Item removeItem(String name) throws VendingMachinePersistenceException {
        Item removedItem = dao.removeItem(name);
        auditDao.writeAuditEntry("Snack " + name + " REMOVED.");
        return removedItem;
    }
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        return dao.getItem(name);
    }

    @Override
    public void updateItem(String name, Item item) throws VendingMachinePersistenceException,
            VendingMachineDataValidationException {
            validateVendingMachineData(item);
        validateVendingMachineData(item);
        dao.updateItem(name, item);
        auditDao.writeAuditEntry("Snack " + item.getName() + " UPDATED.");
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems()
                .stream()
                .filter(item -> item.getQuantity() > 0)
                .collect(Collectors.toList());
    }

    private void validateVendingMachineData(Item item) throws VendingMachineDataValidationException {
        if (item.getName() == null
        || item.getName().trim().length() == 0
        || item.getCost() == null
        || item.getQuantity() < 0
        ) {
            throw new VendingMachineDataValidationException("ERROR: all item fields are required.");
        }
    }
}
