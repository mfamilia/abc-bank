package com.abc;

import java.util.ArrayList;
import java.util.List;

import com.abc.utils.StringUtil;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder sb = new StringBuilder("Statement for ")
            .append(name)
            .append("\n");

        double total = 0.0;
        for (Account a : accounts) {
            sb.append("\n")
                .append(a.getStatement())
                .append("\n");
            total += a.sumTransactions();
        }
        sb.append("\nTotal In All Accounts ").append(StringUtil.toDollars(total));
        return sb.toString();
    }
}
