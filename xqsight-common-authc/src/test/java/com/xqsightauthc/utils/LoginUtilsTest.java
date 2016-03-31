/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsightauthc.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.utils.LoginUtils;

/**
 * 
 * @author linhaoran
 * @version LoginUtilsTest.java, v 0.1 2015年9月30日 上午8:51:58 linhaoran
 */
public class LoginUtilsTest {

    /**
     * Test method for {@link com.saicfc.pmpf.wallet.utils.LoginUtils#judgeLoginType(java.lang.String)}.
     */
    @Test
    public void testJudgeLoginType() {
        assertEquals(LoginTypeEnum.EMAIL, LoginTypeEnum.EMAIL); 
        
        assertEquals(LoginTypeEnum.EMAIL, LoginUtils.judgeLoginType("fsfsf@fsfs.com"));
        assertNotEquals(LoginTypeEnum.EMAIL, LoginUtils.judgeLoginType("fsfsffsfs.com"));
        
        assertNotEquals(LoginTypeEnum.USERID, LoginUtils.judgeLoginType("fsfsffsfs.com"));
        assertEquals(LoginTypeEnum.USERID, LoginUtils.judgeLoginType("fsfsf_fsfscom"));
        assertEquals(LoginTypeEnum.USERID, LoginUtils.judgeLoginType("fsfsf-fsfscom"));
        
        assertEquals(LoginTypeEnum.CELLPHONE, LoginUtils.judgeLoginType("12345678901"));
        assertNotEquals(LoginTypeEnum.CELLPHONE, LoginUtils.judgeLoginType("123456789m"));
        
    }

}
