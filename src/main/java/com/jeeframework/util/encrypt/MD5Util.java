
package com.jeeframework.util.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5Util
{
	private static final String	MD5	= "MD5";

	public static String encrypt(String value)
	{
		if (value == null)
			return "";

		MessageDigest md = null;
		String strDes = null;

		try
		{
			md = MessageDigest.getInstance(MD5);
			md.update(value.getBytes());
			strDes = bytes2Hex(md.digest()); // to HexString
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException(e);
		}
		return strDes;
	}

	public static String bytes2Hex(byte[] byteArray)
	{
		StringBuffer strBuf = new StringBuffer();
		String tmp = null;
		for (int i = 0; i < byteArray.length; i++)
		{
			tmp = Integer.toHexString(byteArray[i] & 0xFF);
			if (tmp.length() == 1)
			{
				strBuf.append("0");
			}
			strBuf.append(tmp);
		}
		return strBuf.toString();
	}

	public static void main(String[] args)
	{
		System.out.println(MD5Util.encrypt("12312412123"));
	}
}
