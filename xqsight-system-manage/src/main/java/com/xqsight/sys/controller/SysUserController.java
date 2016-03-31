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
import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.utils.LoginUtils;
import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.XqsightPageHelper;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sso.authc.service.PasswordHelper;
import com.xqsight.sso.shiro.constants.WebConstants;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.service.SysUserService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/login/")
public class SysUserController extends BaseController {

	@Autowired
	private SysUserService sysLoginService;

	@RequestMapping("save")
	@RequiresPermissions("sys:login:save")
	public Object saveLogin(HttpServletRequest request, SysLogin sysLogin) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysLogin> sysLogins = sysLoginService.querySysLoginByLoginId(sysLogin.getLoginId());
		if (sysLogins != null && sysLogins.size() > 0) {
			map.put(Constants.KEY_STATUS, Constants.SUCCESS);
			map.put(Constants.KEY_MESSAGE, Constants.USER_LOGIN_ID_EXIST_MSG);
			return WebUtils.getResponseBody(request, map);
		}
		sysLogin.setCreateOprId("" + getCurrentUserId());
		sysLogin.setUpdOprId("" + getCurrentUserId());
		sysLogin.setCreateTime(new Date());
		sysLogin.setUpdateTime(new Date());
		sysLogin.setPassword("!password");
		LoginTypeEnum loginType = LoginUtils.judgeLoginType(sysLogin.getLoginId());
		sysLogin.setLoginType(Integer.parseInt(loginType.value()));
		PasswordHelper.encryptPassword(sysLogin);
		sysLoginService.saveSysLogin(sysLogin);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("resetpwd")
	@RequiresPermissions("sys:login:resetpwd")
	public Object resetPwd(HttpServletRequest request, SysLogin sysLogin) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sl = sysLoginService.querySysLoginById(sysLogin.getId());
		sysLogin.setUpdOprId("" + getCurrentUserId());
		sysLogin.setUpdateTime(new Date());
		sysLogin.setCreateTime(sl.getCreateTime());
		sysLogin.setPassword("!password");
		PasswordHelper.encryptPassword(sysLogin);
		sysLoginService.updateSysLoginPwd(sysLogin);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.USER_RESET_PWD_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("delete")
	@RequiresPermissions("sys:login:delete")
	public Object deleteLogin(HttpServletRequest request, int id) {
		logger.debug("删除登录用户Id={}", id);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		sysLoginService.deleteSysLogin(id);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("query")
	public Object queryLoginByName(HttpServletRequest request, SysLogin sysLogin) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页处理
		// every page show count
		String pageSize = request.getParameter("iDisplayLength") == null ? "15" : request.getParameter("iDisplayLength");
		// current page first index
		String pageRowIndex = request.getParameter("iDisplayStart") == null ? "1" : request.getParameter("iDisplayStart");
		// current request times
		String sEcho = request.getParameter("sEcho") == null ? "1" : request.getParameter("sEcho");
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(Integer.parseInt(pageRowIndex), Integer.parseInt(pageSize));
		List<SysLogin> sysLogins = sysLoginService.querySysLogin(sysLogin.getLoginId());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put("sEcho", sEcho);
		map.put("iTotalRecords", page.getTotal());
		map.put("iTotalDisplayRecords", page.getTotal());
		map.put("aaData", sysLogins);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybyid")
	public Object queryLoginById(HttpServletRequest request, int id) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sysLogins = sysLoginService.querySysLoginById(id);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysLogins);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("updpwd")
	public Object updPwd(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sl = sysLoginService.querySysLoginById(getCurrentUserId());
		sl.setUpdOprId("" + getCurrentUserId());
		sl.setUpdateTime(new Date());
		String password = request.getParameter(WebConstants.PASSWORD);
		sl.setPassword(password);
		PasswordHelper.encryptPassword(sl);
		sysLoginService.updateSysLoginPwd(sl);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.USER_RESET_PWD_MSG);
		return WebUtils.getResponseBody(request, map);
	}
}
