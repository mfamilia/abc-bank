package com.abc.accounts;

import com.abc.Account;
import com.abc.Transaction;
import com.abc.utils.DateProvider;

import java.util.Date;

public class MaxiSavings extends Account {
    public static final int RECENT_WITHDRAW_OFFSET = -10;

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
        Date safeWithdrawDate = DateProvider.getInstance().offsetFromNow(RECENT_WITHDRAW_OFFSET);
        for (Transaction t: transactions) {
            if(t.isDebit() && t.date.after(safeWithdrawDate)) {
                return true;
            }
        }
        return false;
    }
}
