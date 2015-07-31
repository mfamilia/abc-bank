package com.abc.accounts;

import com.abc.Account;
import com.abc.AccountType;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class CheckingTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void shouldCreate(){
        Account account = Account.newInstance(AccountType.CHECKING);
        assertTrue(account instanceof Checking);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowExceptionWrongDepositAmount() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(-100);
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowExceptionWrongWithdrawAmount() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.withdraw(0);
    }

    @Test
    public void shouldSumTransactions() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(100.0);
        assertEquals(100.0, account.sumTransactions(), DOUBLE_DELTA);
        account.deposit(200.0);
        assertEquals(300.0, account.sumTransactions(), DOUBLE_DELTA);
        account.withdraw(50.0);
        assertEquals(250.0, account.sumTransactions(), DOUBLE_DELTA);
    }

    @Test
    public void shouldGetStatement() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals("Checking Account\n  deposit $100.00\n" +
                "  withdrawal $50.00\nTotal $50.00", account.getStatement());
    }

    @Test
    public void shouldGetInterestEarned() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(50000.0);
        assertEquals(50.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
