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

import com.github.pagehelper.Page;
import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.XqsightPageHelper;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sys.model.SysRole;
import com.xqsight.sys.service.SysRoleService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/role/")
public class SysRoleController extends BaseController {

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping("save")
	@RequiresPermissions("sys:role:save")
	public Object saveRole(HttpServletRequest request, SysRole sysRole) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysRole.setCreateOprId("" + getCurrentUserId());
		sysRole.setUpdOprId("" + getCurrentUserId());
		sysRole.setCreateTime(new Date());
		sysRole.setUpdateTime(new Date());
		sysRoleService.saveSysRole(sysRole);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("update")
	@RequiresPermissions("sys:role:update")
	public Object updateRole(HttpServletRequest request, SysRole sysRole) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysRole.setUpdOprId("" + getCurrentUserId());
		sysRole.setUpdateTime(new Date());
		sysRoleService.updateSysRole(sysRole);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.UPD_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("delete")
	@RequiresPermissions("sys:role:delete")
	public Object deleteRole(HttpServletRequest request, int roleId) {
		logger.debug("删除角色roleId={}", roleId);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysRoleService.deleteSysRole(roleId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("query")
	public Object queryRoleByName(HttpServletRequest request, SysRole sysRole) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(sysRole.getiDisplayStart(), sysRole.getiDisplayLength());
		List<SysRole> sysRoles = sysRoleService.querySysRoleByRoleName(sysRole.getRoleName());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put("sEcho", sysRole.getsEcho());
		map.put("iTotalRecords", page.getTotal());
		map.put("iTotalDisplayRecords", page.getTotal());
		map.put("aaData", sysRoles);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybyid")
	public Object queryRoleById(HttpServletRequest request, int roleId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysRole sysRoles = sysRoleService.querySysRoleByRoleId(roleId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysRoles);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("queryall")
	public Object queryRoleAll(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysRole> sysRoles = sysRoleService.querySysRoleAll();
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysRoles);
		return WebUtils.getResponseBody(request, map);
	}

}
