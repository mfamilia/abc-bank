package com.abc;

import com.abc.accounts.Checking;
import com.abc.accounts.MaxiSavings;
import com.abc.accounts.Savings;
import com.abc.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

enum AccountType {
    CHECKING {
        public Account create() {
            return new Checking();
        }
    },
    SAVINGS {
        public Account create() {
            return new Savings();
        }
    },
    MAXI_SAVINGS {
        public Account create() {
            return new MaxiSavings();
        }
    };
    public Account create() {
        return null;
    }
}

public abstract class Account {
    public static Account newInstance(AccountType accountType) {
        return accountType.create();
    }

    private List<Transaction> transactions;

    public Account() {
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        checkAmount(amount);
        transactions.add(new Transaction(amount));
    }

    public void withdraw(double amount) {
        checkAmount(amount);
        transactions.add(new Transaction(-amount));
    }

    private void checkAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
    }

    public abstract double interestEarned();

    protected abstract String getStatementDescriptor();

    public String getStatement() {
        String s = getStatementDescriptor();

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : transactions) {
            s += "  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + StringUtil.toDollars(t.amount) + "\n";
            total += t.amount;
        }
        s += "Total " + StringUtil.toDollars(total);
        return s;
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }
}
