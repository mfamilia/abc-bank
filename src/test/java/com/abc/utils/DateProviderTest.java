package com.abc.utils;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class DateProviderTest {
    @Test
    public void testGetDate() {
        assertEquals(Calendar.getInstance().getTime().toString(), DateProvider.getInstance().now().toString());
    }
}
