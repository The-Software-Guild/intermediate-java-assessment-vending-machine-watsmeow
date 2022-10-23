package com.watsmeow.VendingMachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Change {
    BigDecimal change;

    public Change(BigDecimal change) {
        this.change = change;
    }

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
