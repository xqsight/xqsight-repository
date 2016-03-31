/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sso.authc.service.PasswordHelper;
import com.xqsight.sys.model.SysLogin;
import com.xqsight.sys.service.SysUserService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年2月25日 上午10:21:19
 *
 */
@RestController
@RequestMapping("/sys/index/")
public class SysIndexController extends BaseController {
	
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("queryuserinfo")
	public Object queryUserInfo(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sysLogin = sysUserService.querySysLoginById(getCurrentUserId());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA,sysLogin);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("updusername")
	public Object updUserName(HttpServletRequest request, String userName) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sysLogin = new SysLogin();
		sysLogin.setId(getCurrentUser().getId());
		sysLogin.setUserName(userName);
		sysUserService.updUserName(sysLogin);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE,Constants.UPD_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("updpwd")
	public Object updPassword(HttpServletRequest request, String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		SysLogin sysLogin = sysUserService.querySysLoginById(getCurrentUserId());
		sysLogin.setUpdOprId("" + getCurrentUserId());
		sysLogin.setUpdateTime(new Date());
		sysLogin.setPassword(password);
		PasswordHelper.encryptPassword(sysLogin);
		sysUserService.updateSysLoginPwd(sysLogin);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.USER_UPD_PWD_MSG);
		return WebUtils.getResponseBody(request, map);
	}
}
