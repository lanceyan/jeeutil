package com.jeeframework.util.net;

import com.jeeframework.util.validate.Validate;
import org.junit.Assert;
import org.junit.Test;

/**
 * 描述
 *
 * @author lance
 * @version 1.0 2017-09-13 18:55
 */
public class IPUtilTest {
    @Test
    public void getMACByIp() throws Exception {
        Assert.assertTrue(Validate.isEmpty(IPUtil.getMACByIp(null)));
    }

}