/**
 * 上海汽车集团财务有限责任公司
 * Copyright (c) 1994-2015 All Rights Reserved.
 */
package com.xqsight.authc.appcontroller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xqsight.authc.enums.LoginTypeEnum;
import com.xqsight.authc.exceptions.AccountExistsException;
import com.xqsight.authc.exceptions.ValilCodeNoEqualException;
import com.xqsight.authc.model.WalletUserModel;
import com.xqsight.authc.service.WalletUserService;
import com.xqsight.authc.utils.LoginUtils;
import com.xqsight.common.constants.Constants;
import com.xqsight.common.utils.web.WebUtils;
import com.xqsight.sso.authc.SSOUsernamePasswordToken;
import com.xqsight.sso.authc.service.PasswordHelper;
import com.xqsight.sso.enums.UserType;
import com.xqsight.sso.model.UserBaseModel;
import com.xqsight.sso.shiro.constants.WebConstants;
import com.xqsight.sso.subject.SSOSubject;
import com.xqsight.sso.utils.SSOUtils;

/**
 * 
 * @author linhaoran
 * @version SignUpController.java, v 0.1 2015年9月29日 下午11:20:53 linhaoran
 */
@Controller
@RequestMapping("/authc")
public class SignUpController {

	private final static Logger logger = LogManager.getLogger(SignUpController.class);

	private String defaulFinishUrl = "";

	@Autowired
	private WalletUserService walletUserService;

	@RequestMapping("/signup")
	public Object signUp(HttpServletRequest request) {
		String gotoUrl = request.getParameter(WebConstants.GO_TO);
		try {
			// 判断验证码
			checkCode(request);

			doSignUp(request);
		} catch (AccountExistsException e) {
			request.setAttribute(Constants.KEY_MESSAGE, "用户已存在");
			return null;
		} catch (AuthenticationException e) {
			logger.error(e);
			return null;
		} 
		return StringUtils.hasLength(gotoUrl) ? gotoUrl : defaulFinishUrl;
	}

	@ResponseBody
	@RequestMapping("/ajaxSignup")
	public Object ajaxSignUp(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 判断验证码
			checkCode(request);

			SSOUsernamePasswordToken token = doSignUp(request);
			doAfterSignUp(token);

		} catch (AccountExistsException e) {
			map.put(Constants.KEY_STATUS, Constants.FAILURE);
			map.put(Constants.KEY_MESSAGE, "用户已存在");
			return WebUtils.getResponseBody(request, map);
		} catch (AuthenticationException e) {
			logger.error(e);
			if (e instanceof ValilCodeNoEqualException) {
				map.put(Constants.KEY_MESSAGE, "验证码错误");
			}else{
				map.put(Constants.KEY_STATUS, Constants.SUCCESS);
				map.put(Constants.KEY_MESSAGE, "注册成功，登陆发生了异常");
			}
			return WebUtils.getResponseBody(request, map);
		}
		map.put(Constants.KEY_STATUS, Constants.SUCCESS);

		return WebUtils.getResponseBody(request, map);
	}

	/**
	 * 操作个人用户注册流程
	 * 
	 * @param request
	 * @return
	 * @throws AccountExistsException
	 */
	public SSOUsernamePasswordToken doSignUp(HttpServletRequest request) throws AccountExistsException {
		String loginId = request.getParameter(WebConstants.LOGIN_ID);
		String password = request.getParameter(WebConstants.PASSWORD);
		// String loginTypeStr = request.getParameter(WebConstants.LOGIN_TYPE);
		LoginTypeEnum loginType = LoginUtils.judgeLoginType(loginId);// Enum.valueOf(LoginTypeEnum.class,
																		// loginTypeStr);
		UserBaseModel user = null;
		String userId = null;
		String cellPhone = null;
		String email = null;
		switch (loginType) {
		case CELLPHONE:
			user = walletUserService.getByCellPhone(loginId);
			cellPhone = loginId;
			break;
		case EMAIL:
			user = walletUserService.getByEmail(loginId);
			email = loginId;
			break;
		case USERID:
			user = walletUserService.getByUserId(loginId);
			userId = loginId;
			break;
		default:
			logger.error("Not Support LoginType:{}", loginType.toString());
			throw new IllegalArgumentException("Not Support LoginType");
		}

		if (user == null) {
			WalletUserModel model = new WalletUserModel();
			model.setActive(Constants.ACTIVE);
			model.setCellPhone(cellPhone);
			model.setEmail(email);
			model.setUserId(userId);
			model.setUserName(null);
			model.setLocked(Constants.YES);
			model.setImgUri(null);
			model.setPayPassword(null);
			model.setRemark(null);
			model.setCreateTime(new Date());
			// deciphering password with securerandom
			model.setPassword(password);
			PasswordHelper.encryptPassword(model);
			walletUserService.saveUser(model);
		} else {
			throw new AccountExistsException();
		}
		return new SSOUsernamePasswordToken(loginId, password, false, UserType.PERSON);
	}

	/**
	 * 登陆
	 * 
	 * @param token
	 * @throws AuthenticationException
	 */
	private void doAfterSignUp(SSOUsernamePasswordToken token) throws AuthenticationException {
		SSOSubject currentUser = SSOUtils.getCurrentUserSubject();
		if (!currentUser.isAuthenticated()) {
			currentUser.login(token);
		}
	}

	/**
	 * 验证码判断处理
	 * 
	 * @param request
	 * @throws ValilCodeNoEqualException
	 */
	protected void checkCode(HttpServletRequest request) throws ValilCodeNoEqualException {
		HttpSession session = request.getSession();
		String checkCode = request.getParameter(WebConstants.VALIDATA_CODE);
		String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		logger.info("get validataCode:{} && session validataCode:{}", checkCode, code);
		// 判断是否相等
		if (StringUtils.isEmpty(checkCode) || StringUtils.isEmpty(code) || !checkCode.equals(code)) {
			throw new ValilCodeNoEqualException();
		}
	}

}
