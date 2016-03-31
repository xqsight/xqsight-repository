/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.utils.SSOUtils;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月14日 下午3:21:29
 *
 */
public class BaseController {

	protected Logger logger = LogManager.getLogger(getClass());

	/**
	 * 
	 * @Description: 获取当前用户ID
	 *
	 * @Title: getCurrentUserId @param @return 设定文件 @return long 返回类型 @throws
	 */
	protected long getCurrentUserId() {
		UserBaseModel userBaseModel = SSOUtils.getCurrentUser();
		if (userBaseModel != null)
			return userBaseModel.getId();
		return 0;
	}

	/**
	 * 
	 * @Description: 获取当前用户name
	 *
	 * @Title: getCurrentUserName @param @return 设定文件 @return long 返回类型 @throws
	 */
	protected long getCurrentUserName() {
		UserBaseModel userBaseModel = SSOUtils.getCurrentUser();
		if (userBaseModel != null)
			return userBaseModel.getId();
		return 0;
	}

	/**
	 * 
	 * @Description: 获取当前用户对象
	 *
	 * @Title: getCurrentUser @param @return 设定文件 @return UserBaseModel
	 *         返回类型 @throws
	 */
	protected UserBaseModel getCurrentUser() {
		UserBaseModel userBaseModel = SSOUtils.getCurrentUser();
		return userBaseModel;
	}

	@ExceptionHandler
	@ResponseBody
	public Object exception(HttpServletRequest request, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.KEY_STATUS, Constants.FAILURE);
		logger.error("系统出现异常信息，异常信息:{}", ex.getMessage());
		//ex.printStackTrace();
		// 根据不同错误转向不同页面
		if (ex instanceof UnauthorizedException) {
			map.put(Constants.KEY_MESSAGE, Constants.NU_AUTH_ACCESS_MSG);
		} else {
			map.put(Constants.KEY_MESSAGE, Constants.OPR_EXCEPTION_MSG);
		}
		return WebUtils.getResponseBody(request, map);
	}
}
