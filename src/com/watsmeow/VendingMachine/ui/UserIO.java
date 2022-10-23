package com.watsmeow.VendingMachine.ui;

/*
* Public interface that defines what methods are going to be used to communicate with the user
* so that other classes can use them without touching the implementation file
*  */

import java.math.BigDecimal;

public interface UserIO {

    void print(String msg);

    BigDecimal takeMoney(String msg);

    String readString(String msg);
}
