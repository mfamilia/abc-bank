package com.abc.accounts;

import com.abc.Account;
import com.abc.AccountType;

import com.abc.utils.DateProvider;
import org.junit.Test;
import org.mockito.Mockito;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.spi.CalendarDataProvider;

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
                "  withdrawal $50.00\nTotal $50.00", account.statement());
    }

    @Test
    public void testGetInterestEarnedWithNoWithdraw() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(10000.0);
        assertEquals(500.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void testGetInterestEarnedWithWithdraw() {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        account.deposit(11000.0);
        account.withdraw(1000.0);
        assertEquals(10.0, account.interestEarned(), DOUBLE_DELTA);
    }

    @Test
    public void testGetInterestEarnedWithNoRecentWithdrawal() throws ParseException {
        Account account = Account.newInstance(AccountType.MAXI_SAVINGS);
        final DateProvider provider = Mockito.mock(DateProvider.class);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -11);
        Mockito.when(provider.now()).thenReturn(calendar.getTime());
        account.deposit(11000.0);
        Mockito.when(provider.now()).thenReturn(calendar.getTime());
        account.withdraw(1000.0);
        assertEquals("", calendar.getTime().toString());
        assertEquals(500.0, account.interestEarned(), DOUBLE_DELTA);
    }
}
