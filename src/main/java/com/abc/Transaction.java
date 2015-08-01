package com.abc;

import com.abc.utils.DateProvider;

import java.util.Date;

public class Transaction {
    public final double amount;
    public final Date date;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = DateProvider.getInstance().now();
    }

    public boolean isDebit(){
        return amount < 0;
    }
}
