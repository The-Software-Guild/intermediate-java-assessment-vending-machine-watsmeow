package com.watsmeow.VendingMachine.dao;

// This class is used to appropriately throw errors and provide quality error information
public class VendingMachinePersistenceException extends Exception{

    public VendingMachinePersistenceException(String msg) {
        super(msg);
    }
    public VendingMachinePersistenceException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
