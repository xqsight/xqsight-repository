/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.mysqlmapper.SysMenuMapper;
import com.xqsight.sys.service.SysMenuService;
import com.xqsight.sys.utils.MenuUtil;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月30日 下午2:08:06
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

private static Logger logger = LogManager.getLogger(SysMenuServiceImpl.class); 
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	/**
	 * <p>Title: saveSysMenu</p>
	 * <p>Description: </p>
	 * @param sysMenu
	 * @see com.xqsight.sys.service.SysMenuService#saveSysMenu(com.xqsight.sys.model.SysMenu)
	 */
	@Override
	public void saveSysMenu(SysMenu sysMenu) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysMenu));
		sysMenuMapper.saveSysMenu(sysMenu);
	}

	/**
	 * <p>Title: updateSysMenu</p>
	 * <p>Description: </p>
	 * @param sysMenu
	 * @see com.xqsight.sys.service.SysMenuService#updateSysMenu(com.xqsight.sys.model.SysMenu)
	 */
	@Override
	public void updateSysMenu(SysMenu sysMenu) {
		logger.debug("出入参数:{}", JSON.toJSONString(sysMenu));
		sysMenuMapper.updateSysMenu(sysMenu);
	}

	/**
	 * <p>Title: deleteSysMenu</p>
	 * <p>Description: </p>
	 * @param menuId
	 * @see com.xqsight.sys.service.SysMenuService#deleteSysMenu(int)
	 */
	@Override
	public void deleteSysMenu(int menuId) {
		logger.debug("出入参数:{}", menuId);
		sysMenuMapper.deleteSysMenu(menuId);
	}

	/**
	 * <p>Title: querySysMenuByMenuName</p>
	 * <p>Description: </p>
	 * @param menuName
	 * @return
	 * @see com.xqsight.sys.service.SysMenuService#querySysMenuByMenuName(java.lang.String)
	 */
	@Override
	public List<SysMenu> querySysMenuByMenuName(String menuName) {
		logger.debug("出入参数:{}", menuName);
		if(StringUtils.isBlank(menuName)){
			menuName = "%%";
		}else {
			menuName = "%" + menuName + "%";
		}
		List<SysMenu> sysMenus = sysMenuMapper.querySysMenuByMenuName(menuName);
		return MenuUtil.treeMenuList(sysMenus);
	}

	/**
	 * <p>Title: querySysMenu</p>
	 * <p>Description: </p>
	 * @return
	 * @see com.xqsight.sys.service.SysMenuService#querySysMenu()
	 */
	@Override
	public List<SysMenu> querySysMenu() {
		List<SysMenu> sysMenus = sysMenuMapper.querySysMenu();
		return MenuUtil.treeMenuList(sysMenus);
	}

	/**
	 * <p>Title: querySysMenuByMenuId</p>
	 * <p>Description: </p>
	 * @param menuId
	 * @return
	 * @see com.xqsight.sys.service.SysMenuService#querySysMenuByMenuId(int)
	 */
	@Override
	public SysMenu querySysMenuByMenuId(int menuId) {
		return sysMenuMapper.querySysMenuByMenuId(menuId);
	}
	
	

}
