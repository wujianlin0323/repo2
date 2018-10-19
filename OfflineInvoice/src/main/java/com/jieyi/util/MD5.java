package com.jieyi.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

public class MD5 {

    private static final String Algorithm = "md5";
    private static final String code = "utf-8";

    public static String digest(String str, boolean toUpperCase) {
        byte[] b = null;
        MessageDigest md = null;
        try {
            b = str.getBytes(code);
            md = MessageDigest.getInstance(Algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        md.update(b);
        b = md.digest();
        return toUpperCase ? Hex.encodeHexString(b).toUpperCase() : Hex.encodeHexString(b);
    }

    public static byte[] digestByte(String str) {
        byte[] b = null;
        MessageDigest md = null;
        try {
            b = str.getBytes(code);
            md = MessageDigest.getInstance(Algorithm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        md.update(b);
        b = md.digest();
        return b;
    }

    public static void main(String[] args) {
        System.out.println(MD5.digest("测试", true));
    }

}
