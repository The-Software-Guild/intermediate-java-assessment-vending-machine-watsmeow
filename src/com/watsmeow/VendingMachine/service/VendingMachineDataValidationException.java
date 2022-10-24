package com.watsmeow.VendingMachine.service;

// Ensures that data is properly validated before changes are made to the vending machine
public class VendingMachineDataValidationException extends Exception {

    public VendingMachineDataValidationException(String message) {
        super(message);
    }

    public VendingMachineDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
