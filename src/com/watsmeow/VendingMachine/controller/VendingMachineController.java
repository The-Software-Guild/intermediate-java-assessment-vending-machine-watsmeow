package com.watsmeow.VendingMachine.controller;


/*
* This class will instantiate the dao and the view and put them into a constructor.
* It will have a public "run" method.
* It will use the methods from the dao and the view within private methods to make them work together
* */

import com.watsmeow.VendingMachine.dao.VendingMachinePersistenceException;
import com.watsmeow.VendingMachine.dto.Change;
import com.watsmeow.VendingMachine.dto.Item;
import com.watsmeow.VendingMachine.service.VendingMachineDataValidationException;
import com.watsmeow.VendingMachine.service.VendingMachineDuplicateIdException;
import com.watsmeow.VendingMachine.service.VendingMachineServiceLayer;
import com.watsmeow.VendingMachine.ui.VendingMachineView;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class VendingMachineController {

    // Assigning the view into a variable
    private VendingMachineView view;

    // Assigning the service layer into a variable
    private VendingMachineServiceLayer service;

    // Controller constructor taking in service and view as parameters
    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    // Method to run the controller
    public void run() throws VendingMachinePersistenceException,
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException {
        boolean keepGoing = true;

        while (keepGoing) {
            List<Item> allItems = service.getAllItems();
            view.printOptions(allItems);
            String userSelection = getPayment();
            if (userSelection.toLowerCase().equals("exit")) {
                keepGoing = false;
            } else {
                try {
                    BigDecimal userMoney = new BigDecimal(userSelection);
                    userMoney.setScale(2, RoundingMode.HALF_UP);
                    getSelectionProvideChange(userMoney);
                } catch(NumberFormatException e) {
                    view.displayErrorBanner();
                }

            }
        }
        exitMessage();
    }

    // @return user's options from the view
    private String getPayment() {
        return view.userOption();
    }


    /* Method to get the user's money input, compare it to the cost of their item selection,
     * and provide change if necessary
     */
    private void getSelectionProvideChange(BigDecimal userFunds) throws VendingMachinePersistenceException,
            VendingMachineDuplicateIdException,
            VendingMachineDataValidationException {
        String userChoice = view.getSelectionGiveChange().toLowerCase();
        Item item = service.getItem(userChoice);
        if (item == null || item.getQuantity() == 0) {
            view.displayUnknownItemBanner();
        } else {
            if (userFunds.compareTo(item.getCost()) >= 0) {
                if (userFunds.compareTo(item.getCost()) == 1) {
                    BigDecimal change = userFunds.subtract(item.getCost());
                    Change coins = new Change(change);
                    view.displayChangeBanner(coins.provideChange());
                }
                item.setQuantity(item.getQuantity() - 1);
                service.updateItem(userChoice, item);
                view.displayPurchaseSuccessfulBanner();
            } else {
                view.displayInsufficientFundsBanner(userFunds);
            }
        }
    }

    // Displays exit message from view
    private void exitMessage() {
        view.displayExitBanner();
    }

    // Displays unknown command message from view
    private void unknownCommand() {
        view.displayErrorBanner();
    }
}
