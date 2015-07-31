package com.abc;

import com.abc.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    private List<Transaction> transactions;

    private void checkAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        }
    }

    protected abstract String getStatementDescriptor();

    public abstract double interestEarned();

    public static Account newInstance(AccountType accountType) {
        return accountType.create();
    }

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

    public String getStatement() {
        StringBuilder sb = new StringBuilder(getStatementDescriptor());

        double total = 0.0;
        for (Transaction t : transactions) {
            sb.append("  ")
                .append((t.amount < 0 ? "withdrawal " : "deposit "))
                .append(StringUtil.toDollars(t.amount))
                .append("\n");
            total += t.amount;
        }
        sb.append("Total ").append(StringUtil.toDollars(total));
        return sb.toString();
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }
}
