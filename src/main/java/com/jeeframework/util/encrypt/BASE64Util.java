package com.jeeframework.util.encrypt;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;

/**
 * base64工具类
 */
public class BASE64Util {

    public static String encode(byte[] inContent) {
        return Base64.encodeToString(inContent, Base64.NO_WRAP);
    }


    public static String encodeURLLine(byte[] inContent) throws IOException {
        return encodeLine(inContent).replace('+', '-').replace('=', '~');
    }


    public static String encodeLine(byte[] inContent) throws IOException {
        BufferedReader br = null;
        BufferedWriter bw = null;
        StringWriter sw = new StringWriter();
        try {
            String sContent = Base64.encodeToString(inContent, Base64.NO_WRAP);

            br = new BufferedReader(new java.io.StringReader(sContent));
            bw = new BufferedWriter(sw);
            String bRead = null;
            while ((bRead = br.readLine()) != null) {
                bw.write(bRead);
            }

            bw.flush();

            return sw.toString();
        } catch (IOException ex) {
            throw ex;
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (Exception ex) {

            }

            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {

            }
        }
    }


    public static byte[] decode(String inBase64)
            throws IOException {
        return Base64.decode(inBase64, Base64.NO_WRAP);
    }


    public static byte[] decodeURLLine(String inBase64URLLine)
            throws IOException {
        return decode(inBase64URLLine.replace('-', '+').replace('~', '='));
    }
}
