/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sys.model.SysQuickKey;
import com.xqsight.sys.service.SysQuickKeyService;

/**
 * @Description: TODO
 * @author wangganggang
 * @date 2015年12月31日 下午3:24:54
 */
@RestController
@RequestMapping("/sys/quickkey/")
public class SysQuickKeyController extends BaseController {

	@Autowired
	private SysQuickKeyService sysQuickKeyService;

	@RequestMapping("save")
	public Object saveRole(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		String[] funOrders = request.getParameterValues("funOrder");
		String[] keyIcons = request.getParameterValues("keyIcon");
		String[] keyTitles = request.getParameterValues("keyTitle");
		String[] keyValues = request.getParameterValues("keyValue");
		long id = getCurrentUserId();
		List<SysQuickKey> sysQuickKeys = new ArrayList<SysQuickKey>();
		for(int i = 0;i < funOrders.length; i++){
			SysQuickKey sysQuickKey = new SysQuickKey();
			sysQuickKey.setCreateOprId("" + id);
			sysQuickKey.setUpdOprId("" + id);
			sysQuickKey.setCreateTime(new Date());
			sysQuickKey.setUpdateTime(new Date());
			sysQuickKey.setId(id);
			sysQuickKey.setFunOrder(Integer.valueOf(funOrders[i]));
			sysQuickKey.setKeyIcon(keyIcons[i]);
			sysQuickKey.setKeyTitle(keyTitles[i]);
			sysQuickKey.setKeyValue(keyValues[i]);
			sysQuickKeys.add(sysQuickKey);
		}
		sysQuickKeyService.saveSysQuickKey(id,sysQuickKeys);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybyid")
	public Object queryRoleById(HttpServletRequest request) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysQuickKey> sysQuickKeys = sysQuickKeyService.querySysQuickKeyById(getCurrentUserId());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, sysQuickKeys);
		return WebUtils.getResponseBody(request, map);
	}
}
