package com.watsmeow.VendingMachine.dao;

public interface VendingMachineAuditDao {

    /**
     * Method to write an entry to the log file. Throws an exception because there is the potential for it to
     * run into problems when writing to the audit file.
     */
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}
