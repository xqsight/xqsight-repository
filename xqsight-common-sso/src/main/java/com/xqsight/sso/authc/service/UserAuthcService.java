/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sso.authc.service;

import java.util.Set;

import com.xqsight.sso.model.UserBaseModel;

/**
 * 
 * @author linhaoran
 * @version UserAuthcService.java, v 0.1 2015年8月3日 下午5:40:10 linhaoran
 */
public interface UserAuthcService {
    
    /**
     * 添加用户-角色关系
     * @param id
     * @param roleIds
     */
    public void correlationRoles(long id, Long... roleIds);

    /**
     * 移除用户-角色关系
     * @param id
     * @param roleIds
     */
    public void uncorrelationRoles(long id, Long... roleIds);

    /**
     * 根据登陆id查找用户
     * @param loginId
     * @return
     */
    public UserBaseModel findByLoginId(String loginId);
    
    /**
     * 根据用户id查找其角色
     * @param id
     * @return
     */
    public Set<String> findRoles(long id);

    /**
     * 根据用户id查找其权限
     * @param userId
     * @return
     */
    public Set<String> findPermissions(long id);

}
