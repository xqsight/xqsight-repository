/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.shiro.authc;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * 
 * @author linhaoran
 * @version MerchantUserToken.java, v 0.1 2015年8月5日 下午3:04:27 linhaoran
 */
public class MerchantUserToken extends UsernamePasswordToken {

    /**
     * @param userName
     * @param password
     * @param isRememberMe
     */
    public MerchantUserToken(String userName, String password, boolean isRememberMe) {
        super(userName, password, isRememberMe);
    }

    /**  */
    private static final long serialVersionUID = 5563652781901046885L;

}
