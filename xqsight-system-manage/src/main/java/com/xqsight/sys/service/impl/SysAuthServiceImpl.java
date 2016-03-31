/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.mysqlbatchmapper.BSysAuthMapper;
import com.xqsight.sys.mysqlmapper.SysAuthMapper;
import com.xqsight.sys.service.SysAuthService;
import com.xqsight.sys.utils.MenuUtil;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:24:31
 */
@Service
public class SysAuthServiceImpl implements SysAuthService {

	private static Logger logger = LogManager.getLogger(SysAuthServiceImpl.class); 
	
	@Autowired
	private BSysAuthMapper bSysAuthMapper;
	
	@Autowired
	private SysAuthMapper sysAuthMapper;

	/**
	 * <p>Title: saveUserRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @param id
	 * @see com.xqsight.sys.service.SysAuthService#saveUserRole(int, java.lang.String[])
	 */
	@Override
	public void saveUserRole(int roleId, String... ids) {
		logger.debug("传入roleId:{},userId：{}",roleId,JSON.toJSONString(ids));
		bSysAuthMapper.deleUserRole(roleId);
		if(ids != null &&  !"".equals(ids)){
			for(String id : ids){
				bSysAuthMapper.saveUserRole(Integer.parseInt(id), roleId);
			}
		}
	}

	/**
	 * <p>Title: saveMenuRole</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @param menuId
	 * @see com.xqsight.sys.service.SysAuthService#saveMenuRole(int, java.lang.String[])
	 */
	@Override
	public void saveMenuRole(int roleId, String... menuIds) {
		logger.debug("传入roleId:{},userId：{}",roleId,JSON.toJSONString(menuIds));
		bSysAuthMapper.deleMenuRole(roleId);
		if(menuIds != null &&  !"".equals(menuIds)){
			for(String menuId : menuIds){
				bSysAuthMapper.saveMenuRole(Integer.parseInt(menuId), roleId);
			}
		}
	}

	/**
	 * <p>Title: querAuthUser</p>
	 * <p>Description: </p>
	 * @param roleId
	 * @return
	 * @see com.xqsight.sys.service.SysAuthService#querAuthUser(int)
	 */
	@Override
	public List<SysLogin> querAuthUser(int roleId) {
		return sysAuthMapper.queryAuthUser(roleId);
	}

	/**
	 * <p>Title: queryAuthMenu</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @see com.xqsight.sys.service.SysAuthService#queryAuthMenu(int)
	 */
	@Override
	public List<SysMenu> queryAuthMenu(int roleId) {
		List<SysMenu> sysMenus = sysAuthMapper.queryAuthMenu(roleId);
		return MenuUtil.treeMenuList(sysMenus);
	}

	/**
	 * <p>Title: queryCurrentUserMenu</p>
	 * <p>Description: </p>
	 * @param userId
	 * @return
	 * @see com.xqsight.sys.service.SysAuthService#queryCurrentUserMenu(int)
	 */
	@Override
	public List<SysMenu> queryCurrentUserMenu(long userId) {
		List<SysMenu> sysMenus = sysAuthMapper.queryCurrentUserMenu(userId);
		return MenuUtil.treeMenuList(sysMenus);
	}

	/**
	 * <p>Title: queryAllMenu</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.sys.service.SysAuthService#queryAllMenu()
	 */
	@Override
	public List<SysMenu> queryAllMenu() {
		List<SysMenu> sysMenus = sysAuthMapper.queryAllMenu();
		return MenuUtil.treeMenuList(sysMenus);
	}
	
	
}
