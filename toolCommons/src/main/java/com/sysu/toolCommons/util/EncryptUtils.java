package com.sysu.toolCommons.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by adam on 2015/11/17.
 */
public class EncryptUtils {

    private EncryptUtils(){}

    public static String encryptMD5(String src){
        String dest = DigestUtils.md5Hex(src);
        return dest;
    }
}
