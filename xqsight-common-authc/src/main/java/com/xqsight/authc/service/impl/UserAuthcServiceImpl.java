/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.authc.service.WalletUserService;
import com.xqsight.authc.utils.LoginUtils;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.constants.WebConstants;

/**
 * 
 * @author linhaoran
 * @version UserAuthcServiceImpl.java, v 0.1 2015年9月24日 上午11:53:13 linhaoran
 */
@Service("userAuthcService")
public class UserAuthcServiceImpl implements UserAuthcService {
    
    @Autowired(required=false)
    private WalletUserService walletUserService;
    
    /** 
     * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#correlationRoles(long, java.lang.Long[])
     */
    @Override
    public void correlationRoles(long id, Long... roleIds) {
    }

    /** 
     * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#uncorrelationRoles(long, java.lang.Long[])
     */
    @Override
    public void uncorrelationRoles(long id, Long... roleIds) {
    }

    /** 
     * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findByLoginId(java.lang.String)
     */
    @Override
    public UserBaseModel findByLoginId(String loginId) {
        switch (LoginUtils.judgeLoginType(loginId)) {
            case CELLPHONE:
                return walletUserService.getByCellPhone(loginId);
            case EMAIL:
                return walletUserService.getByEmail(loginId);
            default:
                return walletUserService.getByUserId(loginId);
        }
    }
    
    /** 
     * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findRoles(long)
     */
    @Override
    public Set<String> findRoles(long id) {
        return WebConstants.NORMAL_PERSON_ROLLS;
    }

    /** 
     * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findPermissions(long)
     */
    @Override
    public Set<String> findPermissions(long id) {
        return null;
    }

}
