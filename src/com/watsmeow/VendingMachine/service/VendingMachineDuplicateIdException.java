package com.watsmeow.VendingMachine.service;

// Throws an exception and does not allow duplicate data to be added to the vending machine
public class VendingMachineDuplicateIdException extends Exception {

    public VendingMachineDuplicateIdException(String message) {
        super(message);
    }

    public VendingMachineDuplicateIdException(String message,
                                           Throwable cause) {
        super(message, cause);
    }

}
