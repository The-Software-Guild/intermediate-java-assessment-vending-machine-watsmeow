package com.watsmeow.VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Class to utilize enums and provide the user with correct change
public class Change {
    BigDecimal change;

    // Constructor that takes in the change due to the user in BigDecimal format
    public Change(BigDecimal change) {
        this.change = change;
    }

    // Returns change to user in appropriate breakdown of coins as a string
    public String provideChange() {
        int quarters = calculate(Coins.QUARTERS, change);
        change = change.remainder(new BigDecimal(.25));
        int dimes = calculate(Coins.DIMES, change);
        change = change.remainder(new BigDecimal(.10));
        int nickels = calculate(Coins.NICKELS, change);
        change = change.remainder(new BigDecimal(.05));
        int pennies = calculate(Coins.PENNIES, change);
        return String.format("Your change is: %s quarters, %s dimes, %s nickels, " +
                        "%s pennies",
                quarters,
                dimes,
                nickels,
                pennies
        );
    }

    // Calculates the qty of each coin due to the user using the Coins class enums and BigDecimal
    private int calculate(Coins coin, BigDecimal bigDec) {
        switch (coin) {
            case QUARTERS:
                return bigDec.divide(new BigDecimal(.25), RoundingMode.FLOOR).intValue();
            case DIMES:
                return bigDec.divide(new BigDecimal(.10), RoundingMode.FLOOR).intValue();
            case NICKELS:
                return bigDec.divide(new BigDecimal(.05), RoundingMode.FLOOR).intValue();
            case PENNIES:
                return bigDec.divide(new BigDecimal(.01), RoundingMode.FLOOR).intValue();
            default:
                return 0;
        }
    }
}
