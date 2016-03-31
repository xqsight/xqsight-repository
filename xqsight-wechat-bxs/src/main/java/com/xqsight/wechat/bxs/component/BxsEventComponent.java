/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.component;

import java.util.Map;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.xqsight.wechat.bxs.service.WxUserInfoService;

/**
 * @Description: this is use for 
 * @author xqsight-jerry
 * @date 2016年3月22日 下午7:19:22
 */
@Component
public class BxsEventComponent implements WxMpMessageHandler, WxMpMessageMatcher{

	private Logger logger = LogManager.getLogger(getClass());
	
	
	@Autowired
	private WxUserInfoService wechatBxsService;
	
	/**
	 * <p>Title: match</p>
	 * <p>Description: </p>
	 * @param message
	 * @return
	 * @see me.chanjar.weixin.mp.api.WxMpMessageMatcher#match(me.chanjar.weixin.mp.bean.WxMpXmlMessage)
	 */
	@Override
	public boolean match(WxMpXmlMessage message) {
		logger.debug("message:{}",JSON.toJSONString(message));
		return true;
	}

	/**
	 * <p>Title: handle</p>
	 * <p>Description: </p>
	 * @param wxMessage
	 * @param context
	 * @param wxMpService
	 * @param sessionManager
	 * @return
	 * @throws WxErrorException
	 * @see me.chanjar.weixin.mp.api.WxMpMessageHandler#handle(me.chanjar.weixin.mp.bean.WxMpXmlMessage, java.util.Map, me.chanjar.weixin.mp.api.WxMpService, me.chanjar.weixin.common.session.WxSessionManager)
	 */
	@Override
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
			Map<String, Object> context, WxMpService wxMpService,
			WxSessionManager sessionManager) throws WxErrorException {
		if(StringUtils.equalsIgnoreCase(wxMessage.getEventKey(), "V1001_TODAY_FOCUS")){
			WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
			item.setDescription("这个图片好看吗，好看就点击哦！");
			item.setPicUrl("http://img12.360buyimg.com/n3/g10/M00/0A/1F/rBEQWVFCkSQIAAAAAACyLqnAnzIAACJRwLI63gAALJG863.jpg");
			item.setTitle("好看的妹妹图片");
			item.setUrl("www.baidu.com");
			WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
					.fromUser(wxMessage.getToUserName())
					.toUser(wxMessage.getFromUserName())
					.addArticle(item)
					.addArticle(item)
					.build();
			return m;
		}else if(StringUtils.equalsIgnoreCase(wxMessage.getEventKey(), "V3003_CONTACT_US")){
			WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
		    		.fromUser(wxMessage.getToUserName())
		    		.toUser(wxMessage.getFromUserName()).build();
		    	m.setContent("客服专线：18621509689");
			return m;
		}
		return null;
	}
		
}
