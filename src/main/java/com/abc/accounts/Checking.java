package com.abc.accounts;

import com.abc.Account;

public class Checking extends Account {
    public double interestEarned() {
        double amount = sumTransactions();
        return amount * 0.001;
    }

    protected String statementHeader() {
        return "Checking Account\n";
    }
}
