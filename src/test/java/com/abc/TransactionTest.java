package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransactionTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void shouldSetAmount() {
        Transaction transaction = new Transaction(5.0);
        assertEquals(5.0, transaction.amount, DOUBLE_DELTA);
    }
}
