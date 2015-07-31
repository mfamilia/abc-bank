package com.abc.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void shouldPluralize() {
        assertEquals("3 apples", StringUtil.pluralize(3, "apple"));
    }

    @Test
    public void shouldNotPluralize() {
        assertEquals("1 apple", StringUtil.pluralize(1, "apple"));
    }

    @Test
    public void shouldConvertToDollars() {
        assertEquals("$30.00", StringUtil.toDollars(30));
    }
}
