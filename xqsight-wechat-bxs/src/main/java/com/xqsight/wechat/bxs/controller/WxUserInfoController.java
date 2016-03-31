/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.wechat.bxs.model.WxUserInfo;
import com.xqsight.wechat.bxs.service.WxUserInfoService;

/**
 * @Description: this is use for
 * @author xqsight-jerry
 * @date 2016年3月26日 下午7:33:32
 */
@Controller
@RequestMapping("/wxuser/bxs/")
public class WxUserInfoController extends BaseController{

	@Autowired
	private WxUserInfoService wxUserInfoService;

	@RequestMapping("save")
	@ResponseBody
	private Object save(HttpServletRequest request, WxUserInfo wxUserInfo) {
		logger.debug("传入的参数wxUserInfo:{}", JSON.toJSONString(wxUserInfo));
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();

		WxUserInfo wxUserInfos = wxUserInfoService.queryByWxUserCode(wxUserInfo.getWxUserCode());
		if (wxUserInfos != null && StringUtils.isNotBlank(wxUserInfos.getTelPhone())) {
			wxUserInfo.setUpdateTime(new Date());
			wxUserInfoService.update(wxUserInfo);
		}else {
			wxUserInfo.setCreateTime(new Date());
			wxUserInfo.setUpdateTime(new Date());
			wxUserInfoService.save(wxUserInfo);
		}
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_MESSAGE, Constants.SAVE_OK_MSG);
		return WebUtils.getResponseBody(request, map);
	}

	@RequestMapping("querybywxusercode")
	@ResponseBody
	private Object queryByWxUserCode(HttpServletRequest request, String wxUserCode) {
		logger.debug("传入的参数wxUserCode:{}", wxUserCode);
		// 存放信息内容
		Map<String, Object> map = new HashMap<String, Object>();
		WxUserInfo wxUserInfo = wxUserInfoService.queryByWxUserCode(wxUserCode);
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);
		map.put(Constants.KEY_DATA, wxUserInfo);
		return WebUtils.getResponseBody(request, map);
	}
}
