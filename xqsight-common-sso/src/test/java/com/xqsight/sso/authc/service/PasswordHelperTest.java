/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc.service;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

import com.xqsight.common.utils.DateParseUtils;
import com.xqsight.sso.model.UserBaseModel;

/**
 * 
 * @author linhaoran
 * @version PasswordHelperTest.java, v 0.1 2015年9月25日 下午4:41:06 linhaoran
 */
public class PasswordHelperTest {

    /**
     * Test method for {@link com.saicfc.pmpf.sso.authc.service.PasswordHelper#encryptPassword(com.saicfc.pmpf.wallet.model.UserBaseModel)}.
     * @throws ParseException 
     */
    @Test
    public void testEncryptPassword() throws ParseException {        UserBaseModel user = new UserBaseModel();
    user.setId(1l);
    user.setPassword("123456");
    user.setSalt("20b9b8a2295115cf419bad7b6cdc63fa");
    user.setCreateTime(DateParseUtils.yyyy_MM_dd_SPACE_HH_mm_ss("2016-01-25 08:08:09"));
    PasswordHelper.encryptPassword(user);
    System.out.println(user.getPassword());
    System.out.println(user.getSalt());}

    /**
     * Test method for {@link com.saicfc.pmpf.sso.authc.service.PasswordHelper#checkPassword(com.saicfc.pmpf.wallet.model.UserBaseModel, java.lang.String)}.
     * @throws ParseException 
     */
    @Test
    public void testCheckPassword() throws ParseException {
        UserBaseModel user = new UserBaseModel();
        user.setId(1l);
        user.setPassword("7944609c925d18fcddf7c2a81b11b46a8eb40ef17feeb0f04db69e353cb1b073");
        user.setSalt("20b9b8a2295115cf419bad7b6cdc63fa");
        user.setCreateTime(DateParseUtils.yyyy_MM_dd_SPACE_HH_mm_ss("2015-09-25 08:08:09"));
        assertTrue(PasswordHelper.checkPassword(user, "123456"));
    }

}
