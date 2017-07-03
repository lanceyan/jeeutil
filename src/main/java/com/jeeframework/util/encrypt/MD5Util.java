
package com.jeeframework.util.encrypt;

import com.jeeframework.util.validate.Validate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util {
    private static final String MD5 = "MD5";


    public static String encrypt(String value) {
        try {
            return encrypt(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }

    public static String encrypt(String value, String charset) throws UnsupportedEncodingException {
        byte[] valueBytes = null;

        if (value == null)
            return "";

        if (Validate.isEmpty(charset)) {
            charset = "UTF-8";
        }
        valueBytes = value.getBytes(charset);
        return md5Encryt(valueBytes);
    }

    private static String md5Encryt(byte[] valueBytes) {
        MessageDigest md = null;
        String strDes = null;

        try {
            md = MessageDigest.getInstance(MD5);
            md.update(valueBytes);
            strDes = bytes2Hex(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return strDes;
    }

    public static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < byteArray.length; i++) {
            tmp = Integer.toHexString(byteArray[i] & 0xFF);
            if (tmp.length() == 1) {
                strBuf.append("0");
            }
            strBuf.append(tmp);
        }
        return strBuf.toString();
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(MD5Util.encrypt("12312412123"));
        System.out.println(MD5Util.encrypt("水电费水电费"));
        System.out.println(MD5Util.encrypt("水电费水电费", "UTF-8"));
    }
}
