package com.jeeframework.util.reflection;

import org.junit.Test;

/**
 * 描述
 *
 * @author lance
 * @version 1.0 2018-01-17 11:06
 */
public class ReflectionUtilTest {
    @Test
    public void getCallerClass() throws Exception {
        Class clazz = ReflectionUtil.getCallerClass(0);
        System.out.println(clazz);
    }

}