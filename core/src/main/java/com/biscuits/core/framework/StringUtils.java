package com.biscuits.core.framework;

public final class StringUtils {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().equals("");
    }
}
