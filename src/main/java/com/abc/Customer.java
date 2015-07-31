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
        String statement = "Statement for " + name + "\n";
        double total = 0.0;
        // TODO: Figure out efficiency of string += and replacement
        for (Account a : accounts) {
            statement += "\n" + a.getStatement() + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + StringUtil.toDollars(total);
        return statement;
    }
}
