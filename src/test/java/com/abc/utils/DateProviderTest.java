package com.abc.utils;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class DateProviderTest {
    @Test
    public void testGetDate() {
        assertEquals(Calendar.getInstance().getTime().toString(), DateProvider.getInstance().now().toString());
    }

    @Test
    public void testOffsetFromNow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -10);
        assertEquals(calendar.getTime().toString(), DateProvider.getInstance().offsetFromNow(-10).toString());
    }
}
