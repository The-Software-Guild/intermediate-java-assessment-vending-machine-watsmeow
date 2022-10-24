package com.watsmeow.VendingMachine.ui;

/*
* This class displays data fetched from the model layer by the controller and presents that data to the user
* when the user asks for it.
* It:
* - instantiate the UserIO as a private class
* - instantiate the view using a constructor that makes it public, and pass in the UserIO
* - print out menu options to the user
* - print out banner notifications to the use
* - use the methods for getting user input from the UserIOConsoleImpl */

import com.watsmeow.VendingMachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {this.io = io;}

    public void printOptions(List<Item> itemList) {
        for (Item currentItem : itemList) {
            String itemInfo = String.format("Snack: %s ~ Price: %.2f",
                    currentItem.getName(),
                    currentItem.getCost());
            io.print(itemInfo);
        }
    }

    public String userOption() {
        return io.readString("-----Input money for your desired snack----- \n or type \"EXIT\" to exit the program");
    }

    public String getSelectionGiveChange() {
        return io.readString("Type in the name of your desired snack.");
    }

    public void displayUnknownItemBanner() {
        io.print("Item does not exist, choose another item.");
    }

    public void displayChangeBanner(String changeInCoins) {
        io.print(changeInCoins);
    }

    public void displayPurchaseSuccessfulBanner() {
        io.print("Thanks for your purchase!");
    }

    public void displayInsufficientFundsBanner(BigDecimal amount) {
        io.print("$" + amount + " is insufficient for that item.");
    }

    public void displayExitBanner() {
        io.print("See you again when you need another snack.");
    }

    public void displayErrorBanner() {
        io.print("Unknown command");
    }

    // Displays error message
    public void displayErrorMessage(String errorMsg) {
        io.print("ERROR");
        io.print(errorMsg);
    }
}
