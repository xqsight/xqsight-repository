/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.service.SysMenuService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/menu/")
public class SysMenuController extends BaseController{

	@Autowired
	private SysMenuService sysMenuService;

	@RequestMapping("save")
	@RequiresPermissions("sys:menu:save")
	public Object saveMenu(HttpServletRequest request, SysMenu sysMenu) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysMenu.setCreateOprId("" + getCurrentUserId());
		sysMenu.setUpdOprId("" + getCurrentUserId());
		sysMenu.setCreateTime(new Date());
		sysMenu.setUpdateTime(new Date());
		sysMenuService.saveSysMenu(sysMenu);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("update")
	@RequiresPermissions("sys:menu:update")
	public Object updateMenu(HttpServletRequest request, SysMenu sysMenu) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysMenu.setUpdOprId("" + getCurrentUserId());
		sysMenu.setUpdateTime(new Date());
		sysMenuService.updateSysMenu(sysMenu);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.UPD_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("delete")
	@RequiresPermissions("sys:menu:delete")
	public Object deleteMenu(HttpServletRequest request, int menuId) {
		logger.debug("删除菜单menuId={}", menuId);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysMenuService.deleteSysMenu(menuId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("query")
	public Object queryMenuByName(HttpServletRequest request, String menuName) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysMenu> sysMenus = sysMenuService.querySysMenuByMenuName(menuName);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysMenus);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querymenu")
	public Object queryMenu(HttpServletRequest request, SysMenu sysMenu) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysMenu> sysMenus = sysMenuService.querySysMenu();
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysMenus);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybyid")
	public Object queryMenuById(HttpServletRequest request, int menuId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysMenu sysMenu = sysMenuService.querySysMenuByMenuId(menuId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysMenu);
		return WebUtils.getResponseBody(request, map);
	}
}
