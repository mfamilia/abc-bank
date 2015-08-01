package com.abc.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void testQuantityFormatPluralCase() {
        assertEquals("3 apples", StringUtil.quantityFormat(3, "apple"));
    }

    @Test
    public void testQuantityFormatSingularCase() {
        assertEquals("1 apple", StringUtil.quantityFormat(1, "apple"));
    }

    @Test
    public void testConvertToDollars() {
        assertEquals("$30.00", StringUtil.toDollars(30));
    }
}
