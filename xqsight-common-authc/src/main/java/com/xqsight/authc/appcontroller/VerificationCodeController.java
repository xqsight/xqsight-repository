package com.xqsight.authc.appcontroller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("/code")
public class VerificationCodeController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private Producer captchaProducer;

	@RequestMapping("/getCode")
	public void generateCode(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		// 获取验证码
		String capText = captchaProducer.createText();
		logger.info("[captcha]获取的验证码为：{}", capText);

		// 存入session
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		BufferedImage bi = captchaProducer.createImage(capText);

		try {
			ServletOutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.info("返回验证码出错：{}", e.getMessage());
		}
	}
}
