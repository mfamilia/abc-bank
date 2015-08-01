package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){
        Account checkingAccount = Account.newInstance(AccountType.CHECKING);
        Account savingsAccount = Account.newInstance(AccountType.SAVINGS);

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertEquals("Statement for Henry\n" +
            "\n" +
            "Checking Account\n" +
            "  deposit $100.00\n" +
            "Total $100.00\n" +
            "\n" +
            "Savings Account\n" +
            "  deposit $4,000.00\n" +
            "  withdrawal $200.00\n" +
            "Total $3,800.00\n" +
            "\n" +
            "Total In All Accounts $3,900.00", henry.statement());
    }

    @Test
    public void testGetNumberOfAccounts() {
        Customer oscar = new Customer("Oscar")
            .openAccount(Account.newInstance(AccountType.SAVINGS))
            .openAccount(Account.newInstance(AccountType.CHECKING))
            .openAccount(Account.newInstance(AccountType.MAXI_SAVINGS));
        assertEquals(3, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTotalInterestEarned() {
        Account savings = Account.newInstance(AccountType.SAVINGS);
        savings.deposit(10000);
        Account checking = Account.newInstance(AccountType.CHECKING);
        checking.deposit(1000);

        Customer oscar = new Customer("Oscar")
            .openAccount(savings)
            .openAccount(checking);

        assertEquals(20.0, oscar.totalInterestEarned(), DOUBLE_DELTA);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowExceptionWrongTransferAmount() {
        Account savings = Account.newInstance(AccountType.SAVINGS);
        savings.deposit(10000);
        Account checking = Account.newInstance(AccountType.CHECKING);
        checking.deposit(1000);

        Customer oscar = new Customer("Oscar")
                .openAccount(savings)
                .openAccount(checking);

        oscar.transfer(-100, savings, checking);
    }

    @Test
    public void testTransferBetweenAccounts() {
        Account savings = Account.newInstance(AccountType.SAVINGS);
        savings.deposit(10000);
        Account checking = Account.newInstance(AccountType.CHECKING);
        checking.deposit(1000);

        Customer oscar = new Customer("Oscar")
                .openAccount(savings)
                .openAccount(checking);

        oscar.transfer(500, savings, checking);

        assertEquals(9500, savings.sumTransactions(), DOUBLE_DELTA);
        assertEquals(1500, checking.sumTransactions(), DOUBLE_DELTA);
    }
}
