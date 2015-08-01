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

        Customer bob = new Customer("Bob");
        bob.openAccount(Account.newInstance(AccountType.CHECKING))
            .openAccount(Account.newInstance(AccountType.SAVINGS));
        bank.addCustomer(bob);

        assertEquals("Customer Summary\n - John (1 account)\n - Bob (2 accounts)", bank.customerSummary());
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


        assertEquals(152.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
}
