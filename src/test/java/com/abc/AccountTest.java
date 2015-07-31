package com.abc;

import com.abc.accounts.Checking;
import com.abc.accounts.MaxiSavings;
import com.abc.accounts.Savings;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AccountTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void shouldCreateCheckingAccount(){
        Account account = Account.newInstance(AccountType.CHECKING);
        assertTrue(account instanceof Checking);
    }

    @Test
    public void shouldCreateSavingsAccount(){
        Account account = Account.newInstance(AccountType.SAVINGS);
        assertTrue(account instanceof Savings);
    }

    @Test
    public void shouldCreateMaxiSavingsAccount(){
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        assertTrue(account instanceof MaxiSavings);
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
    public void shouldGetStatementForChecking() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals("Checking Account\n  deposit $100.00\n" +
            "  withdrawal $50.00\nTotal $50.00", account.getStatement());
    }

    @Test
    public void shouldGetStatementForSavings() {
        Account account = Account.newInstance(AccountType.SAVINGS);
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals("Savings Account\n  deposit $100.00\n" +
                "  withdrawal $50.00\nTotal $50.00", account.getStatement());
    }

    @Test
    public void shouldGetStatementForMaxiSavings() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(100.0);
        account.withdraw(50.0);
        assertEquals("Maxi Savings Account\n  deposit $100.00\n" +
                "  withdrawal $50.00\nTotal $50.00", account.getStatement());
    }

    @Test
    public void shouldGetInterestEarnedForChecking() {
        Account account = Account.newInstance(AccountType.CHECKING);
        account.deposit(50000.0);
        assertEquals(50.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void shouldGetInterestEarnedForSavings() {
        Account account = Account.newInstance(AccountType.SAVINGS);
        account.deposit(1000.0);
        assertEquals(1.0, account.interestEarned(), DOUBLE_DELTA);
        account.deposit(49000.0);
        assertEquals(99.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void shouldGetInterestEarnedForMaxiSavings() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(1000.0);
        assertEquals(20.0, account.interestEarned(), DOUBLE_DELTA);
        account.deposit(1000.0);
        assertEquals(70.0, account.interestEarned(), DOUBLE_DELTA);
        account.deposit(1000.0);
        assertEquals(170.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
