/**
 * Company:新启信息技术有限责任公司
 * Copyright: Copyright (c) 2011 
 */
package com.xqsight.wechat.bxs.component;

import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSession;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageMatcher;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutTextMessage;

/**
  * @Description: this is use for 
  * @author xqsight-jerry
  * @date 2016年3月17日 下午7:55:00
  */
@Component
public class BxsMessageComponent implements WxMpMessageHandler, WxMpMessageMatcher {

	private Random random = new Random();
	
	private Pattern pattern = Pattern.compile("\\d+");
	
	/**
	 * <p>Title: match</p>
	 * <p>Description: </p>
	 * @param message
	 * @return
	 * @see me.chanjar.weixin.mp.api.WxMpMessageMatcher#match(me.chanjar.weixin.mp.bean.WxMpXmlMessage)
	 */
	@Override
	public boolean match(WxMpXmlMessage message) {
		return isUserAnswering(message) || isUserWantGuessNum(message);
	}
	
	private boolean isUserWantGuessNum(WxMpXmlMessage message){
		return message.getContent().contains("猜数字");
	}
	
	
	private boolean isUserAnswering(WxMpXmlMessage message){
		return pattern.matcher(message.getContent()).matches();
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
	public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
		if (isUserWantGuessNum(wxMessage)) {
	      return letsGo(wxMessage, wxMpService, sessionManager);
	    }

	    if (isUserAnswering(wxMessage)) {
	      return giveHint(wxMessage, wxMpService, sessionManager);
	    }
	    return null;
	}
	
	
	protected WxMpXmlOutMessage letsGo(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {
	    WxSession session = sessionManager.getSession(wxMessage.getFromUserName());
	    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
	    		.fromUser(wxMessage.getToUserName())
	    		.toUser(wxMessage.getFromUserName()).build();
	    if (session.getAttribute("guessing") == null) {
	    	m.setContent("请猜一个100以内的数字");
	    } else {
	    	m.setContent("放弃了吗？那请重新猜一个100以内的数字");
	    }

	    session.setAttribute("guessing", Boolean.TRUE);
	    session.setAttribute("number", random.nextInt(100));
	    return m;
	  }

	protected WxMpXmlOutMessage giveHint(WxMpXmlMessage wxMessage, WxMpService wxMpService, WxSessionManager sessionManager) throws WxErrorException {

	    WxSession session = sessionManager.getSession(wxMessage.getFromUserName());
	    WxMpXmlOutTextMessage m = WxMpXmlOutMessage.TEXT()
	    		.fromUser(wxMessage.getToUserName())
	    		.toUser(wxMessage.getFromUserName()).build();
	    if (session.getAttribute("guessing") == null) {
	      return null;
	    }
	    boolean guessing = (Boolean) session.getAttribute("guessing");
	    if (!guessing) {
	      return null;
	    }

	    int answer = (Integer) session.getAttribute("number");
	    int guessNumber = Integer.valueOf(wxMessage.getContent());
	    if (guessNumber < answer) {
	    	m.setContent("小了");
	    } else if (guessNumber > answer) {
	    	m.setContent("大了");
	    } else {
	    	m.setContent("Bingo!");
	    	session.removeAttribute("guessing");
	    }
	    return m;
	  }

}
