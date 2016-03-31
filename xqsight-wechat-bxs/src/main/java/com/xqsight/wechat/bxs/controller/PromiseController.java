/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.XqsightPageHelper;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.wechat.bxs.model.WxPromise;
import com.xqsight.wechat.bxs.service.PromiseService;
import com.xqsight.wechat.bxs.service.WxUserInfoService;

/**
 * @Description: this is use for
 * @author xqsight-jerry
 * @date 2016年3月26日 下午6:50:28
 */
@Controller
@RequestMapping("/promise/bxs/")
public class PromiseController extends BaseController{

	@Autowired
	private PromiseService promiseService;
	
	@Autowired
	private WxUserInfoService wxUserInfoService;

	@RequestMapping("save")
	@ResponseBody
	private Object save(HttpServletRequest request, WxPromise wxPromise) {
		logger.debug("传入的参数wxPromise:{}", JSON.toJSONString(wxPromise));
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		wxPromise.setCreateTime(new Date());
		wxPromise.setUpdateTime(new Date());
		wxUserInfoService.updateBase(wxPromise);
		promiseService.save(wxPromise);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, long promiseId) {
		logger.debug("传入的参数promiseId:{}", promiseId);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		promiseService.delete(promiseId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	
	@RequestMapping("cancel")
	@ResponseBody
	public Object cancel(HttpServletRequest request, WxPromise wxPromise) {
		logger.debug("传入的参数wxPromise:{}", JSON.toJSONString(wxPromise));
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		promiseService.updateStatus(wxPromise);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.DEL_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("query")
	@ResponseBody
	public Object query(HttpServletRequest request,WxPromise wxPromise) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页处理
		Page<?> page = XqsightPageHelper.startPageWithPageIndex(wxPromise.getiDisplayStart(), wxPromise.getiDisplayLength());
		List<WxPromise> wxPromises = promiseService.query();
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put("sEcho", wxPromise.getsEcho());
		map.put("iTotalRecords", page.getTotal());
		map.put("iTotalDisplayRecords", page.getTotal());
		map.put("aaData", wxPromises);
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("querybywxusercode")
	@ResponseBody
	public Object queryByWxUserCode(HttpServletRequest request,WxPromise wxPromise) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		// 分页处理
		XqsightPageHelper.startPageWithPageIndex(wxPromise.getiDisplayStart(), wxPromise.getiDisplayLength());
		List<WxPromise> wxPromises = promiseService.queryByUserId(wxPromise.getWxUserId());
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, wxPromises);
		map.put("iDisplayStart", wxPromise.getiDisplayStart());
		return WebUtils.getResponseBody(request, map);
	}
	
	@RequestMapping("querybyid")
	@ResponseBody
	public Object queryByPromiseIdCode(HttpServletRequest request,long promiseId) {
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		WxPromise wxPromise = promiseService.querybyId(promiseId);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, wxPromise);
		return WebUtils.getResponseBody(request, map);
	}
}
