/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.model.SysMenu;
import com.xqsight.sys.service.SysAuthService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月8日 上午9:29:25
 */
@RestController
@RequestMapping("/sys/auth/")
public class SysAuthController extends BaseController{

	@Autowired
	private SysAuthService sysAuthService;

	@RequestMapping("saveuserrole")
	@RequiresPermissions("sys:auth:saveuserrole")
	public Object saveUserRole(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		int roleId = StringUtils.isBlank(request.getParameter("roleId")) ? 0 : Integer.parseInt(request.getParameter("roleId"));
		String[] ids = request.getParameterValues("id");
		sysAuthService.saveUserRole(roleId, ids);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("savemenurole")
	@RequiresPermissions("sys:auth:savemenurole")
	public Object saveMenuRole(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		int roleId = StringUtils.isBlank(request.getParameter("roleId")) ? 0 : Integer.parseInt(request.getParameter("roleId"));
		String[] menuIds = request.getParameterValues("menuId");
		sysAuthService.saveMenuRole(roleId, menuIds);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("queryauthuser")
	public Object querAuthUser(HttpServletRequest request,int roleId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysLogin> sysLogins = sysAuthService.querAuthUser(roleId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysLogins);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("querauthmenu")
	public Object querAuthMenu(HttpServletRequest request,int roleId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysMenu> sysMenus = sysAuthService.queryAuthMenu(roleId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysMenus);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("quercurrentusermenu")
	public Object querCurrentUserMenu(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		//TODO 传递当前用的ID
		List<SysMenu> sysMenus = sysAuthService.queryCurrentUserMenu(getCurrentUserId());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysMenus);
		return WebUtils.getResponseBody(request, map);
	}
}
