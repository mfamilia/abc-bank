package com.abc.accounts;

import com.abc.Account;

public class Savings extends Account {
    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000) {
            return amount * 0.001;
        }
        return 1 + (amount-1000) * 0.002;
    }

    protected String statementHeader() {
        return "Savings Account\n";
    }
}
