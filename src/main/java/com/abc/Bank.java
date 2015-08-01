package com.abc;

import java.util.ArrayList;
import java.util.List;
import com.abc.utils.StringUtil;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuilder sb = new StringBuilder("Customer Summary");
        for (Customer c : customers) {
            String statement = StringUtil.quantityFormat(c.getNumberOfAccounts(), "account");
            sb.append("\n - ")
                .append(c.name)
                .append(" (")
                .append(statement).append(")");
        }
        return sb.toString();
    }

    public double totalInterestPaid() {
        double total = 0;
        for(Customer c: customers) {
            total += c.totalInterestEarned();
        }
        return total;
    }
}
