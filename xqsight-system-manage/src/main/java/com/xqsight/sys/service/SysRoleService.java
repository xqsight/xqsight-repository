/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysRole;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysRoleService {

	/**
	 * 新增角色
	 *
	 * @Title: saveSysRole
	 * @Description: TODO
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysRole(SysRole sysRole);
	
	/**
	 * 
	 * updateSysRole 修改
	 * @Title: updateSysRole
	 * @Description: TODO
	 * @param @param sysRole    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysRole(SysRole sysRole);
	
	/**
	 * 
	 * deleteSysRole 删除 
	 *
	 * @Title: deleteSysRole
	 * @Description: TODO
	 * @param @param roleId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysRole(int roleId);
	
	/**
	 * 
	 * querySysRoleByRoleName 查询
	 *
	 * @Title: querySysRoleByRoleName
	 * @Description: TODO
	 * @param @param roleName
	 * @param @return    设定文件
	 * @return List<SysRole>    返回类型
	 * @throws
	 */
	List<SysRole> querySysRoleByRoleName(String roleName);
	
	/**
	 * 
	 * querySysRoleByRoleId 查询
	 *
	 * @Title: querySysRoleByRoleId
	 * @Description: TODO
	 * @param @param roleId
	 * @param @return    设定文件
	 * @return SysRole    返回类型
	 * @throws
	 */
	SysRole querySysRoleByRoleId(int roleId);

	/**
	 * querySysRoleAll查询所有
	 *
	 * @Title: querySysRoleAll
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<SysRole>    返回类型
	 * @throws
	 */
	List<SysRole> querySysRoleAll();
}
