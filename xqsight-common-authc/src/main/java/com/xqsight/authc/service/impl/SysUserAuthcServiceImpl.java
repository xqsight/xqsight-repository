/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xqsight.authc.mysqlmapper.SysUserAuthcMapper;
import com.xqsight.authc.utils.LoginUtils;
import com.xqsight.sso.authc.service.UserAuthcService;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.model.Resource;
import com.xqsight.sso.shiro.model.Role;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午4:35:18
 */
@Service("sysUserAuthcServiceImpl")
public class SysUserAuthcServiceImpl implements UserAuthcService {

	@Autowired(required=false)
	private SysUserAuthcMapper sysUserAuthcMapper;
	/**
	 * <p>Title: correlationRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @param roleIds
	 * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#correlationRoles(long, java.lang.Long[])
	 */
	@Override
	public void correlationRoles(long id, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <p>Title: uncorrelationRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @param roleIds
	 * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#uncorrelationRoles(long, java.lang.Long[])
	 */
	@Override
	public void uncorrelationRoles(long id, Long... roleIds) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * <p>Title: findByLoginId</p>
	 * <p>Description: </p>
	 * @param loginId
	 * @return
	 * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findByLoginId(java.lang.String)
	 */
	@Override
	public UserBaseModel findByLoginId(String loginId) {
		 switch (LoginUtils.judgeLoginType(loginId)) {
	         case CELLPHONE:
	             return sysUserAuthcMapper.queryUserByLoginId(loginId);
	         case EMAIL:
	             return sysUserAuthcMapper.queryUserByLoginId(loginId);
	         default:
	             return sysUserAuthcMapper.queryUserByLoginId(loginId);
		 }
	}

	/**
	 * <p>Title: findRoles</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findRoles(long)
	 */
	@Override
	public Set<String> findRoles(long id) {
		List<Role> roleList = sysUserAuthcMapper.querUserRoleById(id);
		Set<String> roleSet = new HashSet<String>();
		for (Role role : roleList) {
			roleSet.add(role.getRole());
		}
		return roleSet;
	}

	/**
	 * <p>Title: findPermissions</p>
	 * <p>Description: </p>
	 * @param id
	 * @return
	 * @see com.saicfc.pmpf.sso.authc.service.UserAuthcService#findPermissions(long)
	 */
	@Override
	public Set<String> findPermissions(long id) {
		List<Role> roleList = sysUserAuthcMapper.querUserRoleById((int)id);
		Set<String> permissionSet = new HashSet<String>();
		for (Role role : roleList) {
			List<Resource> resourceList = sysUserAuthcMapper.queryResourceByRoleId(role.getId());
			for (Resource resource : resourceList) {
				if(StringUtils.isNotBlank(resource.getPermission()))
					permissionSet.add(resource.getPermission());
			}
		}
		return permissionSet;
	}
}
