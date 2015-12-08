package com.sysu.toolCommons.util;

/**
 * Created by adam on 2015/11/24.
 */
public class AssertUtils {

    private AssertUtils(){}

    public static boolean assertNotNull(Object obj){
        return obj != null;
    }

    public static boolean assertNull(Object obj){
        return obj == null;
    }

    public static boolean assertNotBlank(String str){
        return !str.trim().equals("");
    }

    public static boolean assertBlank(String str){
        return str.trim().equals("");
    }

    public static boolean assertNotNullAndBlank(String str){
        return assertNotNull(str) && assertNotBlank(str);
    }
}
