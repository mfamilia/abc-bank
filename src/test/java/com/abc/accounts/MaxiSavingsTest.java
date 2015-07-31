package com.abc.accounts;

import com.abc.Account;
import com.abc.AccountType;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class MaxiSavingsTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCreateAccount(){
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        assertTrue(account instanceof MaxiSavings);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowExceptionWrongDepositAmount() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(-100);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testThrowExceptionWrongWithdrawAmount() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.withdraw(0);
    }

    @Test
    public void testSumTransactions() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(100.0);
        assertEquals(100.0, account.sumTransactions(), DOUBLE_DELTA);
        account.deposit(200.0);
        assertEquals(300.0, account.sumTransactions(), DOUBLE_DELTA);
        account.withdraw(50.0);
        assertEquals(250.0, account.sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void testGetStatement() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals("Maxi Savings Account\n  deposit $100.00\n" +
                "  withdrawal $50.00\nTotal $50.00", account.getStatement());
    }

    @Test
    public void testGetInterestEarned() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(1000.0);
        assertEquals(20.0, account.interestEarned(), DOUBLE_DELTA);
        account.deposit(1000.0);
        assertEquals(70.0, account.interestEarned(), DOUBLE_DELTA);
        account.deposit(1000.0);
        assertEquals(170.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
