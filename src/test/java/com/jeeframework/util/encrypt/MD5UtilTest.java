package com.jeeframework.util.encrypt;

import org.junit.Before;
import org.junit.Test;

/**
 * 描述
 *
 * @author lance
 * @version 1.0 2017-07-03 10:59
 */
public class MD5UtilTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void encrypt() throws Exception {

        String md5Tmp = MD5Util.encrypt("时代发生的");
        System.out.println(md5Tmp);
    }

}