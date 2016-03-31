/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2016 All Rights Reserved.
 */
package com.xqsight.wechat.bxs.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2016年1月14日 下午3:21:29
 * 
 */
public class BaseController {

	protected Logger logger = LogManager.getLogger(getClass());

	@ExceptionHandler
	@ResponseBody
	public Object exception(HttpServletRequest request, Exception ex) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.KEY_STATUS, Constants.FAILURE);
		logger.error("系统出现异常信息，异常信息:{}", ex.getMessage());
		map.put(Constants.KEY_MESSAGE, Constants.OPR_EXCEPTION_MSG);
		return WebUtils.getResponseBody(request, map);
	}
}
