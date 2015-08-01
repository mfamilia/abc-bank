package com.abc.utils;

import static java.lang.Math.abs;

public class StringUtil {
    public static String quantityFormat(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }

    public static String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
