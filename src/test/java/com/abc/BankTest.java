package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(Account.newInstance(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void testTotalInterestPaid() {
        Bank bank = new Bank();
        Account checkingAccount = Account.newInstance(AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        Account savingsAccount = Account.newInstance(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Joe").openAccount(savingsAccount));

        savingsAccount.deposit(1500.0);

        Account maxiSavingsAccount = Account.newInstance(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Kate").openAccount(maxiSavingsAccount));

        maxiSavingsAccount.deposit(3000.0);


        assertEquals(172.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
