package com.abc.accounts;

import com.abc.Account;
import com.abc.Transaction;
import com.abc.utils.DateProvider;

import java.util.Date;

public class MaxiSavings extends Account {
    public double interestEarned() {
        double amount = sumTransactions();
        if (hasRecentWithdrawal()) {
            return amount * 0.001;
        }
        return amount * 0.05;
    }

    protected String statementHeader() {
        return "Maxi Savings Account\n";
    }

    protected boolean hasRecentWithdrawal() {
        Date safeWithdrawDate = DateProvider.getInstance().daysLater(10);
        for (Transaction t: transactions) {
            System.out.println(t.date.toString());
            if(t.isDebit() && t.date.after(safeWithdrawDate)) {
                return true;
            }
        }
        return false;
    }
}
