/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service;

import java.util.List;

import com.xqsight.sys.model.SysLogin;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 上午11:44:35
 */
public interface SysUserService {

	/**
	 * 新增
	 *
	 * @Title: saveSysLogin
	 * @Description: TODO
	 * @param @param sysLogin    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveSysLogin(SysLogin sysLogin);
	
	/**
	 * 
	 * updateSysLogin 修改
	 * @Title: updateSysLogin
	 * @Description: TODO
	 * @param @param sysLogin    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void  updateSysLoginPwd(SysLogin sysLogin);
	
	/**
	 * 修改用户名
	 * @Description: TODO
	 *
	 * @Title: updUserName
	 * @param @param sysLogin    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void updUserName(SysLogin sysLogin);
	
	/**
	 * 
	 * deleteSysLogin 删除 
	 *
	 * @Title: deleteSysLogin
	 * @Description: TODO
	 * @param @param loginId    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void deleteSysLogin(int id);
	
	/**
	 * 
	 * querySysLogin 查询
	 *
	 * @Title: querySysLogin
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<SysLogin>    返回类型
	 * @throws
	 */
	List<SysLogin> querySysLogin(String loginId);
	
	/**
	 * 
	 * querySysLoginById查询
	 *
	 * @Title: querySysLoginById
	 * @Description: TODO
	 * @param @param id
	 * @param @return    设定文件
	 * @return SysLogin    返回类型
	 * @throws
	 */
	SysLogin querySysLoginById(long id);
	
	
	/**
	 * 
	 * querySysLogin 查询
	 *
	 * @Title: querySysLogin
	 * @Description: TODO
	 * @param @return    设定文件
	 * @return List<SysLogin>    返回类型
	 * @throws
	 */
	List<SysLogin> querySysLoginByLoginId(String loginId);
}
