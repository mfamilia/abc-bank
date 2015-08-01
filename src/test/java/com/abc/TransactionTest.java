package com.abc;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


public class TransactionTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testAmount() {
        Transaction transaction = new Transaction(5.0);
        assertEquals(5.0, transaction.amount, DOUBLE_DELTA);
        assertEquals(Calendar.getInstance().getTime().toString(), transaction.date.toString());
    }
}
